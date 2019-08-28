import { Component, OnInit } from '@angular/core';
import { Client } from 'src/app/models/client';
import { ClienteService } from 'src/app/services/cliente.service.js';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-clientes',
  templateUrl: './clientes.component.html',
  styleUrls: ['./clientes.component.css']
})
export class ClientesComponent implements OnInit {

  clients: Client[];

  constructor(private clienteService: ClienteService) { }

  ngOnInit() {
    this.clienteService.getClientes().subscribe(
      clients => this.clients = clients
    );
  }

  delete(client: Client): void {
    const swalWithBootstrapButtons = Swal.mixin({
      customClass: {
        confirmButton: 'btn btn-success',
        cancelButton: 'btn btn-danger'
      },
      buttonsStyling: false
    })

    swalWithBootstrapButtons.fire({
      title: 'Are you sure?',
      text: `Are you sure want to delete the user ${client.name}?`,
      type: 'warning',
      showCancelButton: true,
      confirmButtonText: 'Yes, delete it!',
      cancelButtonText: 'No, cancel!',
      reverseButtons: true
    }).then((result) => {
      if (result.value) {
        this.clienteService.delete(client.id).subscribe(response => {
          this.clients = this.clients.filter(clientResp => clientResp !== client);
          swalWithBootstrapButtons.fire(
            'Deleted!',
            `User ${client.name} has been deleted.`,
            'success'
          );
        });
      }
    });
  }

}

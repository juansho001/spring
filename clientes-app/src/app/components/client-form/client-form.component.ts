import { Component, OnInit } from '@angular/core';
import { Client } from 'src/app/models/client';
import { ClienteService } from 'src/app/services/cliente.service';
import { Router, ActivatedRoute } from '@angular/router';
import swal from 'sweetalert2';

@Component({
  selector: 'app-client-form',
  templateUrl: './client-form.component.html',
  styleUrls: ['./client-form.component.css']
})
export class ClientFormComponent implements OnInit {

  private client: Client = new Client();
  private titulo = 'Create Client';

  constructor(private clientService: ClienteService,
              private router: Router,
              private activateRoute: ActivatedRoute) { }

  ngOnInit() {
    this.loadClient();
  }

  loadClient(): void {
    this.activateRoute.params.subscribe(params =>{
      const id = params['id'];
      if (id) {
        this.clientService.getCliente(id).subscribe(client => {
            this.client = client;
        });
      }
    });
  }

  create() {
    this.clientService.create(this.client).subscribe(cliente => {
      this.router.navigate(['/clientes']);
      console.log('test');
      swal.fire('New Client',
                `Cliente ${cliente.name} creado con exito`,
                'success');

    });
  }

  update(){
    this.clientService.update(this.client).subscribe(client =>{
      this.router.navigate(['/clientes']);
      swal.fire('Updated< Client',
        `Cliente ${client.name} actualizado con exito`,
        'success');
    });
  }

}

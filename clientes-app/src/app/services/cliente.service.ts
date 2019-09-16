import { Injectable } from '@angular/core';
import { DatePipe } from '@angular/common'
import { Client } from '../models/client';
import { Observable, throwError } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { map, catchError } from 'rxjs/operators';
import Swal from 'sweetalert2';
import { Router } from '@angular/router';


@Injectable({
  providedIn: 'root'
})
export class ClienteService {

  private ENDPOINT = 'http://localhost:8080/api/clients';
  headers = new HttpHeaders({
    'Content-Type': 'application/json',
  });

  constructor(private http: HttpClient,
    private router: Router, ) { }

  getClientes(): Observable<Client[]> {
    return this.http.get<Client[]>(this.ENDPOINT, { headers: this.headers }).pipe(
      map(response => {
        let clients = response as Client[];
        clients.map(client => {
          let datePipe = new DatePipe('es');
          //client.createdAt = datePipe.transform(client.createdAt, 'EEEE dd, MMMM yyyy')
          return client;
        })


        return clients;
      })
    );
  }

  getCliente(id: number): Observable<Client> {
    return this.http.get<Client>(`${this.ENDPOINT}/${id}`, { headers: this.headers }).pipe(
      catchError(err => {
        this.router.navigate(['/clientes'])
        console.log(err.error.message);
        Swal.fire({
          type: 'error',
          title: 'Oops...',
          text: `Something went wrong!, User id ${id} no exist!`,
        });
        return throwError(err);
      })
    );
  }

  create(client: Client): Observable<Client> {
    return this.http.post<Client>(this.ENDPOINT, client, { headers: this.headers }).pipe(
      map((response: any) => response.client as Client),
      catchError(err => {
        if (err.status === 400) {
          return throwError(err);
        }
        console.log(err.error.message);
        Swal.fire({
          type: 'error',
          title: 'Oops...',
          text: `Something went wrong!, error creating client`,
        });
        return throwError(err);
      })
    );
  }

  update(client: Client): Observable<Client> {
    return this.http.put<Client>(`${this.ENDPOINT}/${client.id}`, client, { headers: this.headers }).pipe(
      catchError(err => {
        if (err.status === 400) {
          return throwError(err);
        }
        console.log(err.error.message);
        Swal.fire({
          type: 'error',
          title: 'Oops...',
          text: `Something went wrong!, error updating client`,
        });
        return throwError(err);
      })
    );
  }

  delete(id: number): Observable<Client> {
    return this.http.delete<Client>(`${this.ENDPOINT}/${id}`, { headers: this.headers }).pipe(
      catchError(err => {
        console.log(err.error.message);
        Swal.fire({
          type: 'error',
          title: 'Oops...',
          text: `Something went wrong!, error deleting client`,
        });
        return throwError(err);
      })
    );
  }
}


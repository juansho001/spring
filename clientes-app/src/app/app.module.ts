import { BrowserModule } from '@angular/platform-browser';
import { NgModule, LOCALE_ID } from '@angular/core';

import { AppComponent } from './app.component';
import { NavbarComponent } from './components/shared/navbar/navbar.component';
import { HeaderComponent } from './components/header/header.component';
import { FooterComponent } from './components/footer/footer.component';
import { ClientesComponent } from './components/clientes/clientes.component';
import { ClienteService } from './services/cliente.service';
import { RouterModule, Routes } from '@angular/router';
import { DirectivasComponent } from './components/directivas/directivas.component';
import { HttpClientModule } from '@angular/common/http';
import { ClientFormComponent } from './components/client-form/client-form.component';
import { FormsModule } from '@angular/forms'
import { registerLocaleData } from '@angular/common';
import localeES from '@angular/common/locales/es';

registerLocaleData(localeES, 'es');

const routes: Routes = [
  { path: '', redirectTo: '/clientes', pathMatch: 'full' },
  { path: 'directivas', component: DirectivasComponent },
  { path: 'clientes', component: ClientesComponent },
  { path: 'client/form', component: ClientFormComponent },
  { path: 'client/form/:id', component: ClientFormComponent },
]

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    HeaderComponent,
    FooterComponent,
    ClientesComponent,
    DirectivasComponent,
    ClientFormComponent
  ],
  imports: [
    BrowserModule,
    RouterModule.forRoot(routes),
    HttpClientModule,
    FormsModule

  ],
  providers: [
    ClienteService,
    { provide: LOCALE_ID, useValue: 'es' }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }

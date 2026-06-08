import { Routes } from '@angular/router';
import { Navbar } from './navbar/navbar';
import { Client } from './client/client';
import { CreditComponent } from './credit/credit.component'; // Import CreditComponent
import { App } from './app';

export const routes: Routes = [
  { path: "", redirectTo: "clients", pathMatch: "full" },
  { path: "clients", component: Client },
  { path: "clients/:clientId/credits", component: CreditComponent } // New route for credits
];

import { Routes } from '@angular/router';
import {Navbar} from './navbar/navbar';
import {Client} from './client/client';
import {App} from './app';

export const routes: Routes = [
  {path:"",redirectTo:"clients",pathMatch:"full"},
  {path:"clients",component:Client}
];

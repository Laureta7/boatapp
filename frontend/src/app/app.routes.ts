import { Routes } from '@angular/router';
import { PageNotFoundComponent } from '@app/pages/page-not-found/page-not-found.component';
import { LoginComponent } from '@app/pages/login/login.component';

export const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: '**', component: PageNotFoundComponent },
];

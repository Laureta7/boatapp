import { Routes } from '@angular/router';
import { PageNotFoundComponent } from '@app/pages/page-not-found/page-not-found.component';
import { LoginComponent } from '@app/pages/login/login.component';
import { BoatsComponent } from '@app/pages/boats/boats.component';
import { AuthGuard } from '@guards/auth.guard';

export const routes: Routes = [
  { path: '', component: BoatsComponent, canActivate: [AuthGuard] },
  { path: 'login', component: LoginComponent },
  { path: '**', component: PageNotFoundComponent },
];

import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';

import {HomeComponent} from './home/home.component';
import {ErrorComponent} from './error/error.component';
import {LoginComponent} from './login/login.component';
import {BusesComponent} from './buses/buses.component';
import {BusDetailsComponent} from './bus-details/bus-details.component';
import {PassesComponent} from './passes/passes.component';
import {PassDetailsComponent} from './pass-details/pass-details.component';
import {PersonalCartComponent} from './personal-cart/personal-cart.component';

export const routes: Routes = [
  {path: '', redirectTo: '/home', pathMatch: 'full'},
  {path: 'home', component: HomeComponent},
  {path: 'error', component: ErrorComponent},
  {path: 'login', component: LoginComponent},
  {path: 'buses', component: BusesComponent},
  {path: 'bus/:id', component: BusDetailsComponent},
  {path: 'passes', component: PassesComponent},
  {path: 'pass/:id', component: PassDetailsComponent},
  {path: 'mycart', component: PersonalCartComponent},
  {path: '**', redirectTo: 'error'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}

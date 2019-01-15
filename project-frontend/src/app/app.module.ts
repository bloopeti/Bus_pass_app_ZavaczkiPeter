import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {MatIconModule, MatMenuModule, MatToolbarModule, MatButtonModule, MatTableModule} from '@angular/material';
import {FormsModule} from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';

import {AppRoutingModule} from './app-routing.module';

import {AppComponent} from './app.component';
import {NavbarComponent} from './navbar/navbar.component';
import {HomeComponent} from './home/home.component';
import {ErrorComponent} from './error/error.component';
import {LoginComponent} from './login/login.component';
import {BusesComponent} from './buses/buses.component';
import {BusDetailsComponent} from './bus-details/bus-details.component';
import {PassesComponent} from './passes/passes.component';
import {PassDetailsComponent} from './pass-details/pass-details.component';
import {PersonalCartComponent} from './personal-cart/personal-cart.component';

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    HomeComponent,
    ErrorComponent,
    LoginComponent,
    BusesComponent,
    BusDetailsComponent,
    PassesComponent,
    PassDetailsComponent,
    PersonalCartComponent
  ],
  imports: [
    BrowserModule,
    MatToolbarModule,
    MatMenuModule,
    MatIconModule,
    MatButtonModule,
    MatTableModule,
    FormsModule,
    HttpClientModule,
    AppRoutingModule,
    BrowserAnimationsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}

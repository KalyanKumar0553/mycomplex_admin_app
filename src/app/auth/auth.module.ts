import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AppLoginComponent } from './app-login/app-login.component';
import { AdminLayoutModule } from "../layouts/admin-layout/admin-layout.module";
import { ComponentsModule } from 'app/shared/components/components.module';



@NgModule({
  declarations: [
    AppLoginComponent,
  ],
  imports: [
    CommonModule,
    ComponentsModule
  ]
})
export class AuthModule { }

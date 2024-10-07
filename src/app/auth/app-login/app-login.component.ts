import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthStateConstants } from 'app/shared/constants/constants.enum';
import RouteUrl from 'app/shared/constants/router-url.enum';

@Component({
  selector: 'app-login',
  templateUrl: './app-login.component.html',
  styleUrls: ['./app-login.component.css']
})
export class AppLoginComponent implements OnInit {

  state: String = AuthStateConstants.LOGIN_STATE;
  
  AuthStateConstants: any = AuthStateConstants;
  
  constructor(private router: Router) { }

  ngOnInit(): void {
    this.state = AuthStateConstants.LOGIN_STATE;
  }

  updateStateToForgotPassword() {
    this.state = AuthStateConstants.FORGOT_PASSWORD_STATE;
  }
  
  updateStateToLogin() {
    this.state = AuthStateConstants.LOGIN_STATE;
  }

  login() {
    this.navigateToDashboard();
  }

  navigateToDashboard() {
    this.router.navigateByUrl(RouteUrl.ROUTE_SEPARATOR+RouteUrl.ADMIN_DASHBOARD);
  }
}

import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthStateConstants } from 'app/shared/constants/constants.enum';
import RouteUrl from 'app/shared/constants/router-url.enum';
import { AuthService } from 'app/shared/services/auth-service';
import { ValidationService } from 'app/shared/services/validation-service'
@Component({
  selector: 'app-login',
  templateUrl: './app-login.component.html',
  styleUrls: ['./app-login.component.css']
})
export class AppLoginComponent implements OnInit {

  state: string = AuthStateConstants.LOGIN_STATE;
  email: string = null;
  password: String = null;
  errorMsg = "";

  AuthStateConstants: any = AuthStateConstants;
  
  constructor(private router: Router,private validationService:ValidationService,private authService: AuthService) { }

  ngOnInit(): void {
    this.email = "";
    this.state = AuthStateConstants.LOGIN_STATE;
  }

  updateStateToForgotPassword() {
    this.email = "";
    this.password = "";
    this.errorMsg = "";
    this.state = AuthStateConstants.FORGOT_PASSWORD_STATE;
  }
  
  updateStateToLogin() {
    this.email = "";
    this.errorMsg = "";
    this.state = AuthStateConstants.LOGIN_STATE;
  }

  login() {
    this.errorMsg = "";
    if(!this.email) {
      this.errorMsg = "Please enter email !";
    } else  if(!this.password) {
      this.errorMsg = "Please enter password !";
    }else if(!this.validationService.validateEmail(this.email)) {
      this.errorMsg = "Please enter valid email !";
    } else {
      this.authService.loginUser({"email":this.email,"password":this.password,"isEmailSent":true}).then(res=>{
          this.navigateToDashboard();
      }).catch(err=>{
        this.errorMsg = "Unable To login with credentials !";
      });
        
    }
  }

  navigateToDashboard() {
    this.router.navigateByUrl(RouteUrl.ROUTE_SEPARATOR+RouteUrl.ADMIN_DASHBOARD);
  }

  resetPassword() {
    this.errorMsg = "";
    if(!this.email) {
      this.errorMsg = "Please enter email !";
    } else if(!this.validationService.validateEmail(this.email)) {
      this.errorMsg = "Please enter valid email !";
    } else {
      let result = this.authService.resetPassword({"email":this.email,"isEmailSent":true});
    }
  }
}

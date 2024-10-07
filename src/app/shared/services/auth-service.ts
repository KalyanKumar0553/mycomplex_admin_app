import {HttpParams} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {firstValueFrom, lastValueFrom} from 'rxjs';
import {MyComplexApiService} from './mycomplex-api.service';
import {Router} from '@angular/router';
import RouteUrl from '../constants/router-url.enum';
import { LocalService } from './local-service';
import { ApiUrls } from '../constants/constants.enum';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  public clearDataTimer: any;

  constructor(private apiService:MyComplexApiService,private router: Router,private localService: LocalService){}

  async logoutUser() {
    this.clearTimer();
    this.localService.clearData();
    return this.apiService.update(ApiUrls.LOGOUT,{}).toPromise();
  }

  clearTimer() {
    if(this.clearDataTimer) {
      clearTimeout(this.clearDataTimer);
    }
  }
}

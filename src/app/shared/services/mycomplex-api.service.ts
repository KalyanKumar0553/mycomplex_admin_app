import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ApiHandlerService } from './api-handler.service';
import { environment } from '../../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class MyComplexApiService extends ApiHandlerService {
  
  constructor(httpClient: HttpClient) {
    super(httpClient);
    this.baseUrl = environment.GF_API_ENDPOINT;
  }

}

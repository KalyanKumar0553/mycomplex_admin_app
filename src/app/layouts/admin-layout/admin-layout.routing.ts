import { Routes } from '@angular/router';

import { DashboardComponent } from './dashboard/dashboard.component';
import RouteUrl from 'app/shared/constants/router-url.enum';
import { AdminLayoutComponent } from './admin-layout.component';

export const AdminLayoutRoutes: Routes = [
    { path: 'dashboard',component: DashboardComponent },
];

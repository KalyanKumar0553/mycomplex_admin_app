import { Component, OnInit } from '@angular/core';
import { dashboardRoute, settingsRoute, vendorRoute, reportsRoute, billingRoute, gateKeeperRoute, directoryRoute, complexRoute, facilitiesRoute, inventoryRoute } from 'app/shared/constants/route-constants.enum';
import { RouteInfo } from 'app/shared/models/route-info-dto';
@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.css']
})
export class SidebarComponent implements OnInit {
  
  dashboardRoute: RouteInfo = dashboardRoute;
  complexRoute: RouteInfo[] = complexRoute;
  directoryRoute: RouteInfo[] = directoryRoute;
  gateKeeperRoute: RouteInfo[] = gateKeeperRoute;
  facilitiesRoute: RouteInfo[] = facilitiesRoute;
  billingRoute: RouteInfo[] = billingRoute;
  vendorRoute: RouteInfo = vendorRoute;
  settingsRoute: RouteInfo[] = settingsRoute;
  reportsRoute: RouteInfo = reportsRoute;
  assetsRoute:RouteInfo = inventoryRoute;

  constructor() { }

  ngOnInit() {
  }

  isMobileMenu() {
      if ($(window).width() > 991) {
          return false;
      }
      return true;
  };
}

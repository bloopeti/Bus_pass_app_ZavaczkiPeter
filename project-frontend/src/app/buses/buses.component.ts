import {Component, OnInit} from '@angular/core';
import {Bus} from '../model/bus';
import {BusService} from '../services/bus.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-buses',
  templateUrl: './buses.component.html',
  styleUrls: ['./buses.component.css']
})
export class BusesComponent implements OnInit {

  buses: Bus[] = [];
  displayedColumns: string[] = ['line', 'route', 'details'];
  isUserAdmin: string;

  constructor(private router: Router, private busService: BusService) {
  }

  ngOnInit() {
    this.isUserAdmin = localStorage.getItem('isUserAdmin');
    if (this.isUserAdmin === 'true') {
      this.displayedColumns.reverse().push('id');
      this.displayedColumns.reverse();
    }
    this.getBuses();
  }

  getBuses(): void {
    this.busService.getAllBuses().subscribe(data => this.buses = data);
  }

  viewDetails(id: number): void {
    this.router.navigate(['bus', id]);
  }
}

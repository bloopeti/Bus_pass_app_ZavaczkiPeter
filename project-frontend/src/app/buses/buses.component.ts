import {Component, OnInit} from '@angular/core';
import {Bus} from '../model/bus';
import {BusService} from '../services/bus.service';

@Component({
  selector: 'app-buses',
  templateUrl: './buses.component.html',
  styleUrls: ['./buses.component.css']
})
export class BusesComponent implements OnInit {

  buses: Bus[] = [];
  displayedColumns: string[] = ['line', 'route'];

  constructor(private busService: BusService) {
  }

  ngOnInit() {
    this.getBuses();
  }

  getBuses(): void {
    this.busService.getAllBuses().subscribe(data => this.buses = data);
  }
}

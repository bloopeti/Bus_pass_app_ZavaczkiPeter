import {Component, Input, OnInit} from '@angular/core';
import {Bus} from '../model/bus';
import {environment} from '../environment';
import {ActivatedRoute} from '@angular/router';
import {BusService} from '../services/bus.service';
import {IdWrapper} from '../model/idWrapper';

@Component({
  selector: 'app-bus-details',
  templateUrl: './bus-details.component.html',
  styleUrls: ['./bus-details.component.css']
})
export class BusDetailsComponent implements OnInit {

  @Input() bus = new Bus();
  env = environment;
  id: number;
  private sub: any;

  constructor(private route: ActivatedRoute, private busService: BusService) {
  }

  ngOnInit() {
    this.sub = this.route.params.subscribe(params => {
      this.id = +params['id'];
      this.busService.getBusById(this.id)
        .subscribe(data => {
          this.bus = data;
        });
    });
  }

  add() {
    const busToAdd = new Bus();
    busToAdd.line = this.bus.line.trim();
    busToAdd.route = this.bus.route.trim();
    if (!this.bus.line) {
      return;
    }
    if (!this.bus.route) {
      return;
    }
    this.busService.addBus(busToAdd).subscribe();
  }

  update() {
    this.bus.line = this.bus.line.trim();
    this.bus.route = this.bus.route.trim();
    this.busService.updateBus(this.bus).subscribe();
  }

  delete() {
    const idToDelete = new IdWrapper();
    idToDelete.id = this.bus.id;
    this.busService.deleteBus(idToDelete).subscribe();
  }


}

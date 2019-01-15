import {Component, OnInit} from '@angular/core';
import {Pass} from '../model/pass';
import {PassService} from '../services/pass.service';

@Component({
  selector: 'app-passes',
  templateUrl: './passes.component.html',
  styleUrls: ['./passes.component.css']
})
export class PassesComponent implements OnInit {

  passes: Pass[] = [];
  displayedColumns: string[] = ['type', 'price'];
  currencyCode = 'RON';

  constructor(private passService: PassService) {
  }

  ngOnInit() {
    this.getPasses();
  }

  getPasses(): void {
    this.passService.getAllPasses().subscribe(data => this.passes = data);
  }
}

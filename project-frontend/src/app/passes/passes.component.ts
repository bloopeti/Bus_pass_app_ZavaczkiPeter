import {Component, OnInit} from '@angular/core';
import {Pass} from '../model/pass';
import {PassService} from '../services/pass.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-passes',
  templateUrl: './passes.component.html',
  styleUrls: ['./passes.component.css']
})
export class PassesComponent implements OnInit {

  passes: Pass[] = [];
  displayedColumns: string[] = ['type', 'price', 'details'];
  isUserAdmin: string;
  currencyCode = 'RON';

  constructor(private router: Router, private passService: PassService) {
  }

  ngOnInit() {
    this.isUserAdmin = localStorage.getItem('isUserAdmin');
    if (this.isUserAdmin === 'true') {
      this.displayedColumns.reverse().push('id');
      this.displayedColumns.reverse();
    }
    this.getPasses();
  }

  getPasses(): void {
    this.passService.getAllPasses().subscribe(data => this.passes = data);
  }

  viewDetails(id: number): void {
    this.router.navigate(['pass', id]);
  }
}

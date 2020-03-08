import { Component, OnInit } from '@angular/core';
import {ITEMS} from '../data/data';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  items: string[];
  constructor() { }

  ngOnInit(): void {
    this.items = ITEMS;
  }

}

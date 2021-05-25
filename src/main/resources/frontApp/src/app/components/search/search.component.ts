import { HttpClient } from '@angular/common/http';
import {Component, Output, EventEmitter, Input} from '@angular/core';
import { AppService } from 'src/app/app.service';
import {ESearchOption} from '../../enums/ESearchOption';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html'
})

export class SearchComponent {
  @Input() searchOption: ESearchOption = ESearchOption.all;
  @Output() results = new EventEmitter<any>();

  advanced: boolean = false;
  orientation: string = 'col';
  searchTerm: string = "";
  data: any = null;

  constructor(private http: HttpClient, private service: AppService) {
  }

  search() {
    this.results.emit(this.service.apiPostRequest(this.searchOption, {name: this.searchTerm}));
  }
}

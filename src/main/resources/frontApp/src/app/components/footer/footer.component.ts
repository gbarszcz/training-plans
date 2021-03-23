import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {Navigation} from '../../models/Navigation';
import {ISocialMedia} from '../../models/ISocialMedia';
import {AppService} from '../../app.service';

@Component({
  selector: 'page-footer',
  templateUrl: './footer.component.html',
  styleUrls: ['./footer.component.css']
})

export class FooterComponent implements OnInit {
  @Input() navigation: Navigation | null = null;
  @Input() navPosition = 'bottom';
  @Input() smPosition = '';
  @Input() socialMedia: ISocialMedia[] = [];
  @Output() URLEvent = new EventEmitter<string>();
  footerText = '';
  footerCopyright = '';

  constructor(private appService: AppService) {
  }

  ngOnInit(): void {
    const INFOS = JSON.parse(this.appService.getFooterInfos()).infos || [];

    if (INFOS) {
      this.footerText = INFOS.text || '';
      this.footerCopyright = INFOS.copyright || '';
    }
  }

  public changeURL(url: string): void {
    this.URLEvent.emit(url);
  }
}

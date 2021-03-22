import {Component, Input, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {Navigation} from '../../utils/Navigation';
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
  footerText = '';
  footerCopyright = '';

  constructor(private appService: AppService, private router: Router) {
  }

  ngOnInit(): void {
    const INFOS = JSON.parse(this.appService.getFooterInfos()).infos || [];

    if (INFOS) {
      this.footerText = INFOS.text || '';
      this.footerCopyright = INFOS.copyright || '';
    }
  }
}

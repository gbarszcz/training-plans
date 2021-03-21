import {Component, Input} from '@angular/core';
import {ISocialMedia} from '../../models/ISocialMedia';

@Component({
  selector: 'social-media',
  templateUrl: './social-media.component.html',
  styleUrls: ['./social-media.component.css']
})

export class SocialMediaComponent {
  @Input() position = '';
  @Input() socialMedia: ISocialMedia[] = [];
}

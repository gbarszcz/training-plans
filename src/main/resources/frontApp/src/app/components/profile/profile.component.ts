import {Component} from '@angular/core';
import {IAlert} from '../../models/IAlert';
import {AppService} from '../../app.service';

@Component({
  selector: 'user-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent {
  userName = 'Undefined';
  formData: any = {};
  userData: any = {
    user: [
      {
        value: '',
        icon: 'bi-person',
        placeholder: 'First name',
        name: 'firstName',
        type: 'text',
        error: ''
      },
      {
        value: '',
        icon: 'bi-person',
        placeholder: 'Last name',
        name: 'lastName',
        type: 'text',
        error: ''
      },
      {
        value: '',
        icon: 'bi-person-badge',
        require: true,
        placeholder: 'Identifier',
        name: 'identifier',
        type: 'text',
        error: ''
      },
    ],
    about: [
      {
        value: '',
        icon: 'bi-at',
        require: true,
        placeholder: 'Email',
        name: 'accountEmail',
        type: 'email',
        error: ''
      },
      {
        value: '',
        icon: 'bi-calendar-event',
        placeholder: 'Birthdate',
        name: 'birthdate',
        type: 'date',
        error: ''
      },
      {
        value: '',
        text: 'About you',
        placeholder: 'About you',
        name: 'description',
        type: 'textarea',
        error: ''
      },
    ]
  };
  alerts: IAlert[] = [];

  constructor(private service: AppService) {
    this.prepareFields();
  }

  put(): void {
    this.service.apiPutRequest('profile', this.formData)
      .subscribe(
        (res: any) => {
          this.alerts.push({
            id: this.alerts.length,
            show: true,
            header: 'Ok!',
            text: 'Your changes have been saved',
            level: 'success',
            displayHideButton: true
          });
        },
        (error: any) => {
          if (error.status === 400) {
            this.prepareErrorFields(error.error.errors);
          } else {
            this.alerts.push({
              id: this.alerts.length,
              show: true,
              header: 'Sorry! We have encountered a problem...',
              text: 'Please try again later :\'(',
              level: 'danger',
              displayHideButton: true
            });
          }
        }
      );
  }

  returnIfHasKey(obj: any, key: string): any | boolean {
    return key in obj ? obj[key] : false;
  }

  private prepareFields(): void {
    this.service.apiGetRequest('profile').subscribe(
      (res: any) => {
        this.formData = res;
      },
      (error: any) => {
        console.error(error);
      }
    );
    this.setUserName();
  }

  private prepareErrorFields(errors: any[]): void {
    ['user', 'about'].forEach((variant: string) => {
      errors.forEach((error) => {
        this.userData[variant.toString()].filter((input: any) => {
          return input.name === error.field;
        })[0].error = error.message;
      });
    });
  }

  private setUserName(): string {
    let userName = [this.formData.firstName, this.formData.lastName].filter(Boolean).join(' ');
    if (!!userName) {
      userName = this.formData.identifier || 'Undefined';
    }
    return userName;
  }
}

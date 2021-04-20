import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {AppService} from '../../app.service';
import {IAlert} from '../../models/IAlert';
import {HttpHeaders} from '@angular/common/http';

@Component({
  selector: 'auth-forms',
  templateUrl: './auth-forms.component.html',
  styleUrls: ['./auth-forms.component.css'],
})
export class AuthFormsComponent implements OnInit {
  @Output() URLEvent = new EventEmitter<string>();
  @Input() formType = '';
  formData: any = {};
  inputsParams = [
    {
      placeholder: 'Email',
      name: 'email',
      icon: 'bi-at',
      info: 'This must be an email. For example: name.surname@example.com',
      type: {
        value: 'email',
        error: 'This is a not correct email!'
      },
      max: {
        value: 150,
        error: 'Provided value is too long'
      },
      required: {
        value: true,
        error: 'This is a required field'
      },
      err: {
        isErr: false,
        message: ''
      }
    },
    {
      placeholder: 'Old password',
      name: 'oldPassword',
      icon: 'bi-eye',
      info: 'Enter your actual password.',
      type: {
        value: 'password',
        error: ''
      },
      max: {
        value: 50,
        error: 'Provided value is too long'
      },
      required: {
        value: true,
        error: 'This is a required field'
      },
      err: {
        isErr: false,
        message: ''
      }
    },
    {
      placeholder: 'Password',
      name: 'password',
      icon: 'bi-eye',
      info: 'Min 8 and max 50 characters. Please use: upper and lower letters, special characters [#?!@$%^&*-+], digits.',
      type: {
        value: 'password',
        error: 'This is not correct password format!'
      },
      max: {
        value: 50,
        error: 'Provided value is too long'
      },
      required: {
        value: true,
        error: 'This is a required field'
      },
      err: {
        isErr: false,
        message: ''
      },
      strength: {
        level: '',
        message: ''
      }
    },
    {
      placeholder: 'Repeat password',
      name: 'repeatPassword',
      icon: 'bi-eye',
      info: 'This must be equals to password.',
      type: {
        value: 'password',
        error: 'This field is not equal with password!'
      },
      max: {
        value: 50,
        error: 'Provided value is too long'
      },
      required: {
        value: true,
        error: 'This is a required field'
      },
      err: {
        isErr: false,
        message: ''
      }
    }
  ];
  button = {
    disabled: true,
    text: 'Send'
  };
  alerts: IAlert[] = [];
  infoAlert: IAlert = {
    id: -1,
    show: true,
    header: 'Remember!',
    text: 'By registering and using this website, you acknowledge that you understand and agree to the website regulations.',
    level: 'warning',
    displayHideButton: false
  };

  constructor(private service: AppService) {
  }

  ngOnInit(): void {
    this.formType = this.formType.toLowerCase();
    this.button.text = this.formType;
    this.prepareInputParams();
  }

  submit(): void {
    if (this.isRegisterForm()) {
      this.register();
    } else {
      this.login();
    }
  }

  register(): void {
    this.service.apiPostRequest(this.formType, this.formData).subscribe(
      (res: any) => {
        this.URLEvent.emit('/login');
      },
      (error: any) => {
        if (error.status === 400) {
          this.prepareErrorFields(error.error.errors);
          this.disableButton();
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

  login(): void {
    const HEADERS = {
      headers: new HttpHeaders(this.formData ? {
        authorization: 'Basic ' + btoa(this.formData.email + ':' + this.formData.password)
      } : {})
    };
    this.service.apiGetRequest(this.formType, HEADERS).subscribe(
      (res: any) => {
        sessionStorage.setItem('username', res.username);
        this.URLEvent.emit('/profile');
      },
      (error: any) => {
        this.alerts.push({
          id: this.alerts.length,
          show: true,
          header: error.status === 401 ? 'Oooh nooO !' : 'Sorry! We have encountered a problem...',
          text: error.status === 401 ? 'This credentials are wrong!' : 'Please try again later :\'(',
          level: 'danger',
          displayHideButton: true
        });
      }
    );
  }

  prepareInputParams(): void {
    if (this.isLoginForm()) {
      this.inputsParams = this.inputsParams.filter((input) => {
        return input.name !== 'repeatPassword' && input.name !== 'oldPassword';
      });
    } else if (this.isChangePasswordForm()) {
      this.inputsParams = this.inputsParams.filter((input) => {
        return input.name !== 'email';
      });
    } else {
      this.inputsParams = this.inputsParams.filter((input) => {
        return input.name !== 'oldPassword';
      });
    }
  }

  hideAlert(alertID: string): void {
    this.alerts[+alertID].show = false;
  }

  isLoginForm(): boolean {
    return this.formType === 'login';
  }

  isChangePasswordForm(): boolean {
    return this.formType === 'change password';
  }

  isPassword(index: number): boolean {
    // it must be by the name because the type can be changed
    return (this.inputsParams[index].name === 'password' || this.inputsParams[index].name === 'repeatPassword');
  }

  togglePasswordDisplay(index: number): void {
    this.inputsParams[index].icon = this.inputsParams[index].icon === 'bi-eye' ? 'bi-eye-slash' : 'bi-eye';
    this.inputsParams[index].type.value = this.inputsParams[index].type.value === 'password' ? 'text' : 'password';
  }

  liveValidation(index: number): void {
    if (!this.isLoginForm()) {
      this.registrationValidation(index);
    }
    this.disableButton();
  }

  setErrInfo(target: any, message: string | null): any {
    target.err.isErr = message;
    target.err.message = target.err.isErr ? message : '';
    return target;
  }

  checkRepeatPasswordIsEqual(): boolean {
    return this.formData.password && this.formData.repeatPassword && this.formData.password === this.formData.repeatPassword;
  }

  private registrationValidation(index: number): void {
    let input = this.inputsParams[index];
    const INPUT_VALUE = this.formData[input.name.toString()];

    if (!!input.required.value && !!!INPUT_VALUE) {
      input = this.setErrInfo(input, input.required.error);
    } else if (input.name === 'repeatPassword') {
      input = this.setErrInfo(input, null);

      if (!this.checkRepeatPasswordIsEqual()) {
        if (!!this.formData.password && !!this.formData.repeatPassword) {
          input = this.setErrInfo(input, input.type.error);
        } else if (!!!this.formData.repeatPassword) {
          input = this.setErrInfo(input, 'This field is empty');
        }
      }
    } else if (input.type.value !== 'oldPassword' && !this.isCorrectType(input)) {
      input = this.setErrInfo(input, input.type.error);
    } else if (INPUT_VALUE.length > input.max.value) {
      input = this.setErrInfo(input, input.max.error);
    } else if (input.name === 'password') {
      this.setErrInfo(input, null);
    } else {
      this.setErrInfo(input, null);
    }
    if (input.name === 'password') {
      const REPEAT_PASSWORD = this.inputsParams.filter((inputParam) => {
        return inputParam.name === 'repeatPassword';
      })[0];
      this.setErrInfo(
        REPEAT_PASSWORD,
        !this.checkRepeatPasswordIsEqual() && !!this.formData.repeatPassword ? REPEAT_PASSWORD.type.error : null
      );
      this.passwordStrength(input, input.err.isErr ? null : INPUT_VALUE);
    }
    this.inputsParams[index] = input;
  }

  private prepareErrorFields(errors: any[]): void {
    errors.forEach((error) => {
      this.inputsParams.filter((input) => {
        return input.name === error.field;
      })[0].err = {
        isErr: true,
        message: error.message
      };
    });
  }

  private isCorrectType(input: any): boolean {
    // it must be by the name because the type can be changed
    const INPUT_VALUE = this.formData[input.name.toString()];
    let re;
    switch (input.name) {
      case 'email':
        re = /\S+@\S+\.\S+/;
        return re.test(INPUT_VALUE);
      case 'password':
        re = new RegExp('^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-+]).{8,' + input.max.value + '}$');
        return re.test(INPUT_VALUE);
      default:
        return (typeof INPUT_VALUE === 'string');
    }
  }

  private disableButton(): void {
    const ERR_COUNT = this.inputsParams.filter((inputParam) => {
      return inputParam.err.isErr || (inputParam.required && !this.formData[inputParam.name.toString()]);
    }).length;
    this.button.disabled = ERR_COUNT > 0;
  }

  private passwordStrength(input: any, password: string | null): void {
    const PASS_STRENGTH = this.calculatePasswordStrength(password || '', 8, input.max.value);
    if (PASS_STRENGTH === 0) {
      input.strength.level = '';
      input.strength.message = '';
    } else if (PASS_STRENGTH < 9) {
      input.strength.level = 'danger';
      input.strength.message = 'Weak';
    } else if (PASS_STRENGTH >= 9 && PASS_STRENGTH <= 10) {
      input.strength.level = 'warning';
      input.strength.message = 'Medium';
    } else {
      input.strength.level = 'success';
      input.strength.message = 'Strong';
    }
  }

  private calculatePasswordStrength(value: string, min: number, max: number): number {
    const LOWERCASES_REGEX = /[a-z]/;
    const UPPERCASES_REGEX = /[A-Z]/;
    const SPECIALCASES_REGEX = /[#?!@$%^&*-+]/;
    const DIGITS_REGEX = /[0-9]/;

    let strength = 0;
    if (LOWERCASES_REGEX.test(value)) {
      ++strength;
    }
    if (UPPERCASES_REGEX.test(value)) {
      ++strength;
    }
    if (DIGITS_REGEX.test(value)) {
      ++strength;
    }
    if (new RegExp('^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-+]).{' + min + ',' + max + '}$').test(value)) {
      ++strength;
    }
    if (SPECIALCASES_REGEX.test(value)) {
      strength += 2;
    }

    const VALUE_LENGTH = value.length;
    strength += VALUE_LENGTH * min / max;
    strength += (VALUE_LENGTH - value.replace(DIGITS_REGEX, '').length) * min / max * 1.25;
    strength += (VALUE_LENGTH - value.replace(UPPERCASES_REGEX, '').length) * min / max;
    strength += (VALUE_LENGTH - value.replace(LOWERCASES_REGEX, '').length) * min / max;
    strength += (VALUE_LENGTH - value.replace(SPECIALCASES_REGEX, '').length) * min / max * 1.7;
    strength += String.prototype.concat(...new Set(value)).length / max * 2;
    strength += this.countCharacterTypeChanges(value) / max * 2;

    return strength;
  }

  private countCharacterTypeChanges(value: string): number {
    const LOWERCASES_REGEX = /[a-z]{3,}/;
    const UPPERCASES_REGEX = /[A-Z]{3,}/;
    const SPECIALCASES_REGEX = /[#?!@$%^&*-+]{2,}/;
    const DIGITS_REGEX = /[0-9]{3,}/;

    const CHANGES = value.replace(SPECIALCASES_REGEX, '')
      .replace(LOWERCASES_REGEX, '')
      .replace(UPPERCASES_REGEX, '')
      .replace(DIGITS_REGEX, '').length;
    return value.length - CHANGES;
  }
}

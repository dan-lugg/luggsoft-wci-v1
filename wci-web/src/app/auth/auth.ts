import {Injectable} from '@angular/core';

@Injectable()
export class AuthorizationService {
  constructor(private pingUrl: string) {
  }

  private get pingTargetUrl(): string {
    return `${this.pingUrl}&TargetResource=${encodeURI(`${window.location.pathname}${window.location.search}`)}`;
  }

  getToken(): string {

    return '';
  }

  doLogin() {
    window.location.replace(this.pingTargetUrl);
  }

  getMockToken(): string {
    return '';
  }
}

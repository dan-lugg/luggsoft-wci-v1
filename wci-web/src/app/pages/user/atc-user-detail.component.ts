import {Component} from '@angular/core';

@Component({
    template: `
        <div class="row">
            <div class="col">
                <h1>User Profile</h1>
            </div>
        </div>
        <div class="row">
            <div class="col">
                <table class="table table-bordered">
                    <tbody>
                        <tr>
                            <th>Username</th>
                            <td>{{this.user.name}}</td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
    `
})
export class AtcUserDetailComponent {
    user: User = {
        name: '',
    };

    constructor() {
    }

    getUserDetails(): void {
        // TODO: Get user details
    }
}


export interface User {
    name: string;
}

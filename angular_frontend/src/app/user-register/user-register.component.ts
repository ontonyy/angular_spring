import {Component, OnInit} from '@angular/core';

@Component({
  selector: 'app-user-register',
  templateUrl: './user-register.component.html',
  styleUrls: ['./user-register.component.css']
})
export class UserRegisterComponent implements OnInit{
  interests: string[] = []
  userName: string = "";
  userInterest: string = "";

  ngOnInit(): void {
    this.interests = [
      "Sport",
      "Music",
      "Books",
      "Programming",
      "Fashion"
    ]
  }


  register(username: string, userInterest: string) {
    console.log(`Register: ${username} - ${userInterest}`)
  }
}

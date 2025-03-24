import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '@env/environment';

interface Boat {
  id: string;
  description: string;
  year: number;
  length: number;
  ownerName: string;
  price: number;
  registrationNumber: string;
}

@Component({
  selector: 'app-boats',
  templateUrl: './boats.component.html',
  styleUrls: ['./boats.component.css'],
})
export class BoatsComponent implements OnInit {
  title = 'boats';
  boats: Boat[] = [];

  private apiUrl = environment.apiUrl;

  constructor(private http: HttpClient) {}

  ngOnInit(): void {
    this.getBoats();
  }

  getBoats(): void {
    this.http.get<Boat[]>(`${this.apiUrl}/boats`).subscribe({
      next: (response) => {
        this.boats = response;
        console.log(this.boats);
      },
      error: (error) => {
        console.error('Error fetching boats:', error);
      },
    });
  }
}

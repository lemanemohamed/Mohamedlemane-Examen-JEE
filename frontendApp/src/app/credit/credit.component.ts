import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router'; // Import Router
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { CommonModule } from '@angular/common';

interface CreditData {
  id: number;
  montant: number;
  dateCredit: string;
  // Add other credit properties as needed
}

@Component({
  selector: 'app-credit',
  standalone: true,
  imports: [CommonModule, HttpClientModule],
  templateUrl: './credit.component.html',
  styleUrl: './credit.component.css',
})
export class CreditComponent implements OnInit {
  clientId: number | null = null;
  credits: CreditData[] = [];

  constructor(
    private route: ActivatedRoute,
    private http: HttpClient,
    private router: Router // Inject Router
  ) {}

  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
      this.clientId = Number(params.get('clientId'));
      if (this.clientId) {
        this.loadCredits(this.clientId);
      }
    });
  }

  loadCredits(clientId: number): void {
    // Assuming your backend API for credits is something like /api/clients/{clientId}/credits
    this.http.get<CreditData[]>(`http://localhost:8080/api/credits/client/${clientId}`).subscribe({
      next: (data) => {
        this.credits = data;
      },
      error: (error) => {
        console.error(`Error fetching credits for client ${clientId}:`, error);
      }
    });
  }

  goBack(): void {
    this.router.navigate(['/clients']); // Navigate back to the client list
  }
}

import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { CommonModule } from '@angular/common';

interface ClientData {
  id: number;
  nom: string;
  email: string;
}

@Component({
  selector: 'app-client',
  standalone: true,
  imports: [CommonModule, HttpClientModule],
  templateUrl: './client.html',
  styleUrl: './client.css',
})
export class Client implements OnInit {
  clients: ClientData[] = [];

  constructor(private http: HttpClient) {}

  ngOnInit(): void {
    this.loadClients();
  }

  loadClients(): void {
    this.http.get<ClientData[]>('http://localhost:8080/api/clients').subscribe({
      next: (data) => {
        this.clients = data;
      },
      error: (error) => {
        console.error('Error fetching clients:', error);
      }
    });
  }

  deleteClient(clientId: number): void {
    if (confirm('Are you sure you want to delete this client?')) {
      this.http.delete(`http://localhost:8080/api/clients/${clientId}`).subscribe({
        next: () => {
          console.log(`Client with ID ${clientId} deleted successfully.`);
          this.loadClients(); // Refresh the client list
        },
        error: (error) => {
          console.error(`Error deleting client with ID ${clientId}:`, error);
        }
      });
    }
  }

  showCredits(clientId: number): void {
    console.log(`Showing credits for client with ID: ${clientId}`);
    // TODO: Implement navigation to credits page or display credit details
    // Example: this.router.navigate(['/credits', clientId]);
  }
}

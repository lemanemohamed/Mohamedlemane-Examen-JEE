import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router'; // Import Router

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

  constructor(private http: HttpClient, private router: Router) {} // Inject Router

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

  deleteClient(id: number): void {
    if (confirm('Are you sure you want to delete this client?')) {
      this.http.delete(`http://localhost:8080/api/clients/${id}`).subscribe({
        next: () => {
          console.log(`Client with ID ${id} deleted successfully.`);
          this.loadClients(); // Refresh the client list
        },
        error: (error) => {
          console.error(`Error deleting client with ID ${id}:`, error);
        }
      });
    }
  }

  showCredits(clientId: number): void {
    this.router.navigate(['/clients', clientId, 'credits']);
  }
}

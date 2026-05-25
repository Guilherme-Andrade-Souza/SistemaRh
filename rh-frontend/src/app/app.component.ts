// src/app/app.component.ts
import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, MatToolbarModule, MatIconModule, MatButtonModule],
  template: `
    <div class="app-wrapper">
      <!-- ── Navbar ── -->
      <header class="navbar">
        <div class="navbar-inner container">
          <div class="navbar-brand">
            <div class="brand-icon">
              <mat-icon>corporate_fare</mat-icon>
            </div>
            <div class="brand-text">
              <span class="brand-name">Sistema RH</span>
              <span class="brand-subtitle">Gestão de Recursos Humanos</span>
            </div>
          </div>
          <nav class="navbar-nav">
            <a class="nav-link active" routerLink="/funcionarios">
              <mat-icon>people</mat-icon>
              Funcionários
            </a>
          </nav>
        </div>
      </header>

      <!-- ── Conteúdo ── -->
      <main class="main-content">
        <router-outlet />
      </main>

      <!-- ── Footer ── -->
      <footer class="footer">
        <span>Sistema RH © 2025 — Desenvolvido com Spring Boot + Angular</span>
      </footer>
    </div>
  `,
  styles: [`
    .app-wrapper {
      min-height: 100vh;
      display: flex;
      flex-direction: column;
      background: var(--color-bg);
    }

    /* ── Navbar ── */
    .navbar {
      position: sticky;
      top: 0;
      z-index: 100;
      background: rgba(26, 29, 39, 0.85);
      backdrop-filter: blur(20px);
      border-bottom: 1px solid var(--color-border);
    }

    .navbar-inner {
      display: flex;
      align-items: center;
      justify-content: space-between;
      height: 64px;
    }

    .navbar-brand {
      display: flex;
      align-items: center;
      gap: 12px;
    }

    .brand-icon {
      width: 40px;
      height: 40px;
      border-radius: 10px;
      background: linear-gradient(135deg, var(--color-primary), var(--color-accent));
      display: flex;
      align-items: center;
      justify-content: center;
      color: white;
      font-size: 20px;
    }

    .brand-text {
      display: flex;
      flex-direction: column;
    }

    .brand-name {
      font-size: 18px;
      font-weight: 700;
      color: #f1f5f9;
      line-height: 1;
    }

    .brand-subtitle {
      font-size: 11px;
      color: #64748b;
      margin-top: 2px;
    }

    /* ── Nav links ── */
    .navbar-nav {
      display: flex;
      align-items: center;
      gap: 4px;
    }

    .nav-link {
      display: flex;
      align-items: center;
      gap: 6px;
      padding: 8px 16px;
      border-radius: 8px;
      color: #94a3b8;
      text-decoration: none;
      font-size: 14px;
      font-weight: 500;
      transition: all 0.2s;
    }

    .nav-link:hover,
    .nav-link.active {
      color: #f1f5f9;
      background: rgba(124, 110, 248, 0.12);
    }

    .nav-link mat-icon {
      font-size: 18px;
      width: 18px;
      height: 18px;
    }

    /* ── Main ── */
    .main-content {
      flex: 1;
      padding: 32px 0;
    }

    /* ── Footer ── */
    .footer {
      border-top: 1px solid var(--color-border);
      padding: 16px 24px;
      text-align: center;
      font-size: 12px;
      color: #475569;
    }
  `],
})
export class AppComponent {}

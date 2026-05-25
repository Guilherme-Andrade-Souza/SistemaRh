// src/app/features/funcionarios/lista/funcionarios-lista.component.ts
import {
  Component, OnInit, inject, ChangeDetectionStrategy, signal, computed
} from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatTableModule } from '@angular/material/table';
import { MatPaginatorModule, PageEvent } from '@angular/material/paginator';
import { MatSortModule } from '@angular/material/sort';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatDialogModule, MatDialog } from '@angular/material/dialog';
import { MatSnackBar, MatSnackBarModule } from '@angular/material/snack-bar';
import { MatTooltipModule } from '@angular/material/tooltip';
import { MatProgressBarModule } from '@angular/material/progress-bar';
import { MatChipsModule } from '@angular/material/chips';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { FormsModule } from '@angular/forms';

import { FuncionarioService } from '../../../core/services/funcionario.service';
import {
  FuncionarioResponse, HIERARCHICAL_LEVEL_LABELS,
  StatusFuncionario, STATUS_LABELS,
} from '../../../core/models/funcionario.model';
import { FuncionarioFormComponent } from '../form/funcionario-form.component';

@Component({
  selector: 'app-funcionarios-lista',
  standalone: true,
  changeDetection: ChangeDetectionStrategy.OnPush,
  imports: [
    CommonModule, FormsModule,
    MatTableModule, MatPaginatorModule, MatSortModule,
    MatButtonModule, MatIconModule, MatDialogModule,
    MatSnackBarModule, MatTooltipModule, MatProgressBarModule,
    MatChipsModule, MatInputModule, MatFormFieldModule,
  ],
  template: `
    <div class="container fade-in">

      <!-- ── Dashboard cards ── -->
      <section class="dashboard-cards">
        <div class="stat-card">
          <div class="stat-icon" style="background: linear-gradient(135deg, #7c6ef8, #9d92ff)">
            <mat-icon>people</mat-icon>
          </div>
          <div class="stat-info">
            <span class="stat-value">{{ totalFuncionarios() }}</span>
            <span class="stat-label">Total</span>
          </div>
        </div>
        <div class="stat-card">
          <div class="stat-icon" style="background: linear-gradient(135deg, #34d399, #10b981)">
            <mat-icon>check_circle</mat-icon>
          </div>
          <div class="stat-info">
            <span class="stat-value">{{ ativos() }}</span>
            <span class="stat-label">Ativos</span>
          </div>
        </div>
        <div class="stat-card">
          <div class="stat-icon" style="background: linear-gradient(135deg, #fbbf24, #f59e0b)">
            <mat-icon>beach_access</mat-icon>
          </div>
          <div class="stat-info">
            <span class="stat-value">{{ emFerias() }}</span>
            <span class="stat-label">Em Férias</span>
          </div>
        </div>
        <div class="stat-card">
          <div class="stat-icon" style="background: linear-gradient(135deg, #f87171, #ef4444)">
            <mat-icon>person_off</mat-icon>
          </div>
          <div class="stat-info">
            <span class="stat-value">{{ inativosOuAfastados() }}</span>
            <span class="stat-label">Inativos/Afastados</span>
          </div>
        </div>
      </section>

      <!-- ── Barra de ações ── -->
      <div class="actions-bar">
        <div class="search-box">
          <mat-form-field appearance="outline" class="search-field">
            <mat-icon matPrefix>search</mat-icon>
            <input
              matInput
              placeholder="Buscar por nome..."
              [(ngModel)]="searchTerm"
              (ngModelChange)="onSearch()"
            />
          </mat-form-field>
        </div>
        <button mat-flat-button class="add-btn" (click)="openForm()">
          <mat-icon>person_add</mat-icon>
          Novo Funcionário
        </button>
      </div>

      <!-- ── Tabela ── -->
      <div class="table-wrapper card">
        <mat-progress-bar *ngIf="loading()" mode="indeterminate" class="loader" />

        <table mat-table [dataSource]="funcionarios()" class="rh-table">

          <!-- Nome -->
          <ng-container matColumnDef="nomeFuncionario">
            <th mat-header-cell *matHeaderCellDef>Funcionário</th>
            <td mat-cell *matCellDef="let f">
              <div class="employee-cell">
                <div class="avatar">{{ getInitials(f.nomeFuncionario) }}</div>
                <div class="employee-info">
                  <span class="employee-name">{{ f.nomeFuncionario }}</span>
                  <span class="employee-email">{{ f.email }}</span>
                </div>
              </div>
            </td>
          </ng-container>

          <!-- Cargo -->
          <ng-container matColumnDef="cargo">
            <th mat-header-cell *matHeaderCellDef>Cargo</th>
            <td mat-cell *matCellDef="let f">
              <div class="cargo-cell">
                <span class="cargo-title">{{ f.cargo }}</span>
                <span class="nivel-tag">{{ getNivelLabel(f.nivelHierarquico) }}</span>
              </div>
            </td>
          </ng-container>

          <!-- Telefone -->
          <ng-container matColumnDef="telefonePrimario">
            <th mat-header-cell *matHeaderCellDef>Telefone</th>
            <td mat-cell *matCellDef="let f">
              <span class="phone-text">{{ f.telefonePrimario }}</span>
            </td>
          </ng-container>

          <!-- Status -->
          <ng-container matColumnDef="statusFuncionario">
            <th mat-header-cell *matHeaderCellDef>Status</th>
            <td mat-cell *matCellDef="let f">
              <span [class]="'badge ' + getStatusClass(f.statusFuncionario)">
                <span class="badge-dot"></span>
                {{ getStatusLabel(f.statusFuncionario) }}
              </span>
            </td>
          </ng-container>

          <!-- Ações -->
          <ng-container matColumnDef="acoes">
            <th mat-header-cell *matHeaderCellDef></th>
            <td mat-cell *matCellDef="let f">
              <div class="actions-cell">
                <button
                  mat-icon-button
                  matTooltip="Editar"
                  class="edit-btn"
                  (click)="openForm(f); $event.stopPropagation()"
                >
                  <mat-icon>edit</mat-icon>
                </button>
                <button
                  mat-icon-button
                  matTooltip="Excluir"
                  class="delete-btn"
                  (click)="confirmDelete(f); $event.stopPropagation()"
                >
                  <mat-icon>delete</mat-icon>
                </button>
              </div>
            </td>
          </ng-container>

          <tr mat-header-row *matHeaderRowDef="displayedColumns"></tr>
          <tr mat-row *matRowDef="let row; columns: displayedColumns;" class="table-row"></tr>

          <!-- Sem dados -->
          <tr class="mat-row" *matNoDataRow>
            <td class="mat-cell empty-state" colspan="5">
              <mat-icon>search_off</mat-icon>
              <p>Nenhum funcionário encontrado</p>
            </td>
          </tr>
        </table>

        <mat-paginator
          [length]="totalElements()"
          [pageSize]="pageSize"
          [pageSizeOptions]="[5, 10, 25]"
          (page)="onPageChange($event)"
          showFirstLastButtons
          class="paginator"
        />
      </div>
    </div>
  `,
  styles: [`
    /* ── Dashboard ── */
    .dashboard-cards {
      display: grid;
      grid-template-columns: repeat(auto-fit, minmax(200px, 1fr));
      gap: 16px;
      margin-bottom: 28px;
    }

    .stat-card {
      background: var(--color-surface);
      border: 1px solid var(--color-border);
      border-radius: var(--radius-md);
      padding: 20px;
      display: flex;
      align-items: center;
      gap: 16px;
      transition: all 0.2s ease;
    }

    .stat-card:hover {
      border-color: rgba(124, 110, 248, 0.3);
      transform: translateY(-2px);
      box-shadow: var(--shadow-glow);
    }

    .stat-icon {
      width: 48px;
      height: 48px;
      border-radius: 12px;
      display: flex;
      align-items: center;
      justify-content: center;
      color: white;
      flex-shrink: 0;
    }

    .stat-info { display: flex; flex-direction: column; }

    .stat-value {
      font-size: 28px;
      font-weight: 700;
      color: #f1f5f9;
      line-height: 1;
    }

    .stat-label {
      font-size: 13px;
      color: #64748b;
      margin-top: 4px;
    }

    /* ── Actions bar ── */
    .actions-bar {
      display: flex;
      align-items: center;
      justify-content: space-between;
      gap: 16px;
      margin-bottom: 20px;
    }

    .search-field {
      width: 320px;
    }

    .add-btn {
      background: linear-gradient(135deg, #7c6ef8, #9d92ff) !important;
      color: white !important;
      font-weight: 600;
      padding: 0 20px;
      height: 44px;
      display: flex;
      align-items: center;
      gap: 8px;
      white-space: nowrap;
      flex-shrink: 0;
    }

    /* ── Table wrapper ── */
    .table-wrapper {
      position: relative;
      overflow: hidden;
      padding: 0;
    }

    .loader {
      position: absolute;
      top: 0;
      left: 0;
      right: 0;
      z-index: 10;
    }

    .rh-table {
      width: 100%;
      background: transparent !important;
    }

    /* ── Header ── */
    th.mat-mdc-header-cell {
      background: rgba(124, 110, 248, 0.05) !important;
      color: #94a3b8 !important;
      font-size: 12px !important;
      font-weight: 600 !important;
      text-transform: uppercase;
      letter-spacing: 0.5px;
      border-bottom: 1px solid var(--color-border) !important;
      padding: 16px 20px !important;
    }

    td.mat-mdc-cell {
      border-bottom: 1px solid rgba(255,255,255,0.05) !important;
      padding: 16px 20px !important;
    }

    .table-row {
      transition: background 0.15s;
      cursor: default;
    }

    .table-row:hover td {
      background: rgba(124, 110, 248, 0.05) !important;
    }

    /* ── Employee cell ── */
    .employee-cell {
      display: flex;
      align-items: center;
      gap: 12px;
    }

    .avatar {
      width: 40px;
      height: 40px;
      border-radius: 50%;
      background: linear-gradient(135deg, #7c6ef8, #f472b6);
      color: white;
      font-size: 13px;
      font-weight: 700;
      display: flex;
      align-items: center;
      justify-content: center;
      flex-shrink: 0;
    }

    .employee-info { display: flex; flex-direction: column; }
    .employee-name { font-weight: 600; color: #f1f5f9; font-size: 14px; }
    .employee-email { font-size: 12px; color: #64748b; margin-top: 2px; }

    /* ── Cargo cell ── */
    .cargo-cell { display: flex; flex-direction: column; gap: 4px; }
    .cargo-title { color: #cbd5e1; font-size: 14px; }
    .nivel-tag {
      font-size: 11px;
      color: #7c6ef8;
      background: rgba(124, 110, 248, 0.1);
      border-radius: 4px;
      padding: 2px 8px;
      width: fit-content;
      font-weight: 500;
    }

    .phone-text { color: #94a3b8; font-size: 14px; font-family: monospace; }

    /* ── Badge ── */
    .badge {
      display: inline-flex;
      align-items: center;
      gap: 6px;
      padding: 4px 12px;
      border-radius: 20px;
      font-size: 11px;
      font-weight: 600;
      letter-spacing: 0.5px;
      text-transform: uppercase;
    }

    .badge-dot {
      width: 6px;
      height: 6px;
      border-radius: 50%;
    }

    .badge-ativo    { background: rgba(52, 211, 153, 0.15); color: #34d399; border: 1px solid rgba(52, 211, 153, 0.3); }
    .badge-ativo .badge-dot { background: #34d399; }
    .badge-inativo  { background: rgba(248, 113, 113, 0.15); color: #f87171; border: 1px solid rgba(248, 113, 113, 0.3); }
    .badge-inativo .badge-dot { background: #f87171; }
    .badge-ferias   { background: rgba(251, 191, 36, 0.15); color: #fbbf24; border: 1px solid rgba(251, 191, 36, 0.3); }
    .badge-ferias .badge-dot { background: #fbbf24; }
    .badge-afastado { background: rgba(96, 165, 250, 0.15); color: #60a5fa; border: 1px solid rgba(96, 165, 250, 0.3); }
    .badge-afastado .badge-dot { background: #60a5fa; }

    /* ── Actions ── */
    .actions-cell { display: flex; gap: 4px; }
    .edit-btn { color: #7c6ef8 !important; }
    .delete-btn { color: #f87171 !important; }

    /* ── Empty state ── */
    .empty-state {
      text-align: center;
      padding: 60px !important;
      color: #475569;
    }

    .empty-state mat-icon {
      font-size: 48px;
      width: 48px;
      height: 48px;
      opacity: 0.4;
    }

    .empty-state p {
      margin-top: 12px;
      font-size: 16px;
    }

    /* ── Paginator ── */
    .paginator {
      background: transparent !important;
      border-top: 1px solid var(--color-border);
      color: #94a3b8;
    }
  `],
})
export class FuncionariosListaComponent implements OnInit {
  private readonly service = inject(FuncionarioService);
  private readonly dialog = inject(MatDialog);
  private readonly snackBar = inject(MatSnackBar);

  readonly displayedColumns = ['nomeFuncionario', 'cargo', 'telefonePrimario', 'statusFuncionario', 'acoes'];

  // State
  funcionarios = signal<FuncionarioResponse[]>([]);
  totalElements = signal(0);
  loading = signal(false);
  searchTerm = '';
  pageSize = 10;
  currentPage = 0;

  // Computed stats
  totalFuncionarios = computed(() => this.totalElements());
  ativos = computed(() => this.funcionarios().filter(f => f.statusFuncionario === 'ATIVO').length);
  emFerias = computed(() => this.funcionarios().filter(f => f.statusFuncionario === 'FERIAS').length);
  inativosOuAfastados = computed(() =>
    this.funcionarios().filter(f => f.statusFuncionario === 'INATIVO' || f.statusFuncionario === 'AFASTADO').length
  );

  ngOnInit(): void {
    this.loadFuncionarios();
  }

  loadFuncionarios(): void {
    this.loading.set(true);
    this.service.getAll(this.currentPage, this.pageSize).subscribe({
      next: (page) => {
        this.funcionarios.set(page.content);
        this.totalElements.set(page.totalElements);
        this.loading.set(false);
      },
      error: () => {
        this.loading.set(false);
        this.showError('Erro ao carregar funcionários. Verifique se o servidor está rodando.');
      },
    });
  }

  onPageChange(event: PageEvent): void {
    this.currentPage = event.pageIndex;
    this.pageSize = event.pageSize;
    this.loadFuncionarios();
  }

  onSearch(): void {
    this.currentPage = 0;
    this.loadFuncionarios();
  }

  openForm(funcionario?: FuncionarioResponse): void {
    const dialogRef = this.dialog.open(FuncionarioFormComponent, {
      width: '700px',
      maxWidth: '95vw',
      panelClass: 'rh-dialog',
      data: { funcionario },
    });

    dialogRef.afterClosed().subscribe((result) => {
      if (!result) return;

      this.loading.set(true);
      const request$ = funcionario
        ? this.service.update(funcionario.id, result)
        : this.service.create(result);

      request$.subscribe({
        next: () => {
          this.loadFuncionarios();
          this.showSuccess(funcionario ? 'Funcionário atualizado!' : 'Funcionário cadastrado!');
        },
        error: (err) => {
          this.loading.set(false);
          const msg = err?.error?.message ?? 'Erro ao salvar funcionário.';
          this.showError(msg);
        },
      });
    });
  }

  confirmDelete(funcionario: FuncionarioResponse): void {
    if (!confirm(`Deseja excluir "${funcionario.nomeFuncionario}"? Esta ação não pode ser desfeita.`)) {
      return;
    }

    this.loading.set(true);
    this.service.delete(funcionario.id).subscribe({
      next: () => {
        this.loadFuncionarios();
        this.showSuccess('Funcionário removido.');
      },
      error: () => {
        this.loading.set(false);
        this.showError('Erro ao excluir funcionário.');
      },
    });
  }

  // ── Helpers ─────────────────────────────────────

  getInitials(name: string): string {
    return name
      .split(' ')
      .slice(0, 2)
      .map((n) => n[0])
      .join('')
      .toUpperCase();
  }

  getStatusLabel(status: StatusFuncionario): string {
    return STATUS_LABELS[status] ?? status;
  }

  getStatusClass(status: StatusFuncionario): string {
    const map: Record<StatusFuncionario, string> = {
      ATIVO: 'badge-ativo',
      INATIVO: 'badge-inativo',
      FERIAS: 'badge-ferias',
      AFASTADO: 'badge-afastado',
    };
    return map[status] ?? '';
  }

  getNivelLabel(nivel: string): string {
    return HIERARCHICAL_LEVEL_LABELS[nivel as keyof typeof HIERARCHICAL_LEVEL_LABELS] ?? nivel;
  }

  private showSuccess(message: string): void {
    this.snackBar.open(`✅ ${message}`, '', { duration: 3000, panelClass: 'snack-success' });
  }

  private showError(message: string): void {
    this.snackBar.open(`❌ ${message}`, 'Fechar', { duration: 6000 });
  }
}

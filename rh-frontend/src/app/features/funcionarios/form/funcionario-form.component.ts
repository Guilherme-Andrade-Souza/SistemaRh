// src/app/features/funcionarios/form/funcionario-form.component.ts
import { Component, inject, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import {
  FormBuilder,
  FormGroup,
  Validators,
  ReactiveFormsModule,
} from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA, MatDialogModule } from '@angular/material/dialog';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import {
  FuncionarioResponse,
  HIERARCHICAL_LEVEL_LABELS,
  HierarchicalLevel,
  StatusFuncionario,
  STATUS_LABELS,
} from '../../../core/models/funcionario.model';

export interface FuncionarioFormData {
  funcionario?: FuncionarioResponse;
}

@Component({
  selector: 'app-funcionario-form',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule,
    MatDialogModule,
    MatFormFieldModule,
    MatInputModule,
    MatSelectModule,
    MatButtonModule,
    MatIconModule,
    MatProgressSpinnerModule,
  ],
  template: `
    <div class="dialog-header">
      <div class="dialog-title-row">
        <div class="dialog-icon">
          <mat-icon>{{ isEditMode ? 'edit' : 'person_add' }}</mat-icon>
        </div>
        <div>
          <h2 mat-dialog-title>{{ isEditMode ? 'Editar Funcionário' : 'Novo Funcionário' }}</h2>
          <p class="dialog-subtitle">{{ isEditMode ? 'Atualize os dados abaixo' : 'Preencha os dados do novo colaborador' }}</p>
        </div>
      </div>
      <button mat-icon-button mat-dialog-close class="close-btn">
        <mat-icon>close</mat-icon>
      </button>
    </div>

    <mat-dialog-content class="dialog-content">
      <form [formGroup]="form" class="form-grid">

        <!-- Nome -->
        <mat-form-field appearance="outline" class="full-width">
          <mat-label>Nome completo</mat-label>
          <mat-icon matPrefix>badge</mat-icon>
          <input matInput formControlName="nomeFuncionario" placeholder="Ex: João da Silva" />
          <mat-error *ngIf="form.get('nomeFuncionario')?.hasError('required')">
            Nome é obrigatório
          </mat-error>
        </mat-form-field>

        <!-- CPF -->
        <mat-form-field appearance="outline">
          <mat-label>CPF</mat-label>
          <mat-icon matPrefix>fingerprint</mat-icon>
          <input matInput formControlName="cpf" placeholder="000.000.000-00" maxlength="14" />
          <mat-error *ngIf="form.get('cpf')?.hasError('required')">CPF é obrigatório</mat-error>
          <mat-error *ngIf="form.get('cpf')?.hasError('pattern')">Formato: 000.000.000-00</mat-error>
        </mat-form-field>

        <!-- E-mail -->
        <mat-form-field appearance="outline">
          <mat-label>E-mail</mat-label>
          <mat-icon matPrefix>email</mat-icon>
          <input matInput formControlName="email" type="email" placeholder="joao@empresa.com" />
          <mat-error *ngIf="form.get('email')?.hasError('required')">E-mail é obrigatório</mat-error>
          <mat-error *ngIf="form.get('email')?.hasError('email')">E-mail inválido</mat-error>
        </mat-form-field>

        <!-- Telefone Primário -->
        <mat-form-field appearance="outline">
          <mat-label>Telefone principal</mat-label>
          <mat-icon matPrefix>phone</mat-icon>
          <input matInput formControlName="telefonePrimario" placeholder="(11) 99999-9999" />
          <mat-error *ngIf="form.get('telefonePrimario')?.hasError('required')">Telefone é obrigatório</mat-error>
          <mat-error *ngIf="form.get('telefonePrimario')?.hasError('pattern')">Formato: (XX) 9XXXX-XXXX</mat-error>
        </mat-form-field>

        <!-- Telefone Secundário -->
        <mat-form-field appearance="outline">
          <mat-label>Telefone secundário (opcional)</mat-label>
          <mat-icon matPrefix>phone_in_talk</mat-icon>
          <input matInput formControlName="telefoneSegundario" placeholder="(11) 98888-8888" />
          <mat-error *ngIf="form.get('telefoneSegundario')?.hasError('pattern')">Formato: (XX) 9XXXX-XXXX</mat-error>
        </mat-form-field>

        <!-- Cargo -->
        <mat-form-field appearance="outline">
          <mat-label>Cargo</mat-label>
          <mat-icon matPrefix>work</mat-icon>
          <input matInput formControlName="cargo" placeholder="Ex: Desenvolvedor Backend" />
          <mat-error *ngIf="form.get('cargo')?.hasError('required')">Cargo é obrigatório</mat-error>
        </mat-form-field>

        <!-- Nível Hierárquico -->
        <mat-form-field appearance="outline">
          <mat-label>Nível Hierárquico</mat-label>
          <mat-icon matPrefix>leaderboard</mat-icon>
          <mat-select formControlName="nivelHierarquico">
            <mat-option *ngFor="let nivel of niveisOptions" [value]="nivel.value">
              {{ nivel.label }}
            </mat-option>
          </mat-select>
          <mat-error *ngIf="form.get('nivelHierarquico')?.hasError('required')">Nível é obrigatório</mat-error>
        </mat-form-field>

        <!-- Status -->
        <mat-form-field appearance="outline">
          <mat-label>Status</mat-label>
          <mat-icon matPrefix>toggle_on</mat-icon>
          <mat-select formControlName="statusFuncionario">
            <mat-option *ngFor="let status of statusOptions" [value]="status.value">
              {{ status.label }}
            </mat-option>
          </mat-select>
          <mat-error *ngIf="form.get('statusFuncionario')?.hasError('required')">Status é obrigatório</mat-error>
        </mat-form-field>

      </form>
    </mat-dialog-content>

    <mat-dialog-actions class="dialog-actions">
      <button mat-stroked-button mat-dialog-close class="cancel-btn">
        Cancelar
      </button>
      <button
        mat-flat-button
        class="save-btn"
        [disabled]="form.invalid || saving"
        (click)="onSubmit()"
      >
        <mat-spinner *ngIf="saving" diameter="18" />
        <mat-icon *ngIf="!saving">{{ isEditMode ? 'save' : 'person_add' }}</mat-icon>
        {{ isEditMode ? 'Salvar alterações' : 'Cadastrar' }}
      </button>
    </mat-dialog-actions>
  `,
  styles: [`
    :host {
      display: block;
      font-family: var(--font-family, 'Inter', sans-serif);
    }

    /* Header */
    .dialog-header {
      display: flex;
      align-items: flex-start;
      justify-content: space-between;
      padding: 24px 24px 0;
    }

    .dialog-title-row {
      display: flex;
      align-items: center;
      gap: 14px;
    }

    .dialog-icon {
      width: 48px;
      height: 48px;
      border-radius: 12px;
      background: linear-gradient(135deg, #7c6ef8, #f472b6);
      display: flex;
      align-items: center;
      justify-content: center;
      color: white;
    }

    [mat-dialog-title] {
      margin: 0 !important;
      font-size: 20px !important;
      font-weight: 700 !important;
      color: #f1f5f9 !important;
    }

    .dialog-subtitle {
      font-size: 13px;
      color: #64748b;
      margin-top: 2px;
    }

    .close-btn {
      color: #64748b !important;
    }

    /* Content */
    .dialog-content {
      padding: 24px !important;
      max-height: 65vh;
    }

    .form-grid {
      display: grid;
      grid-template-columns: 1fr 1fr;
      gap: 8px;
    }

    .full-width {
      grid-column: 1 / -1;
    }

    mat-form-field {
      width: 100%;
    }

    /* Material dark overrides */
    ::ng-deep .mat-mdc-form-field-outline {
      border-color: rgba(255, 255, 255, 0.15) !important;
    }

    ::ng-deep .mat-mdc-text-field-wrapper {
      background: rgba(255, 255, 255, 0.04) !important;
    }

    /* Actions */
    .dialog-actions {
      display: flex !important;
      justify-content: flex-end;
      gap: 12px;
      padding: 16px 24px !important;
      border-top: 1px solid rgba(255, 255, 255, 0.08);
    }

    .cancel-btn {
      color: #94a3b8 !important;
      border-color: rgba(255, 255, 255, 0.12) !important;
    }

    .save-btn {
      background: linear-gradient(135deg, #7c6ef8, #9d92ff) !important;
      color: white !important;
      display: flex;
      align-items: center;
      gap: 6px;
      font-weight: 600;
      padding: 0 20px;
    }

    .save-btn:disabled {
      opacity: 0.5;
      cursor: not-allowed;
    }
  `],
})
export class FuncionarioFormComponent implements OnInit {
  private readonly fb = inject(FormBuilder);
  private readonly dialogRef = inject(MatDialogRef<FuncionarioFormComponent>);
  readonly data: FuncionarioFormData = inject(MAT_DIALOG_DATA);

  form!: FormGroup;
  saving = false;

  readonly niveisOptions = Object.entries(HIERARCHICAL_LEVEL_LABELS).map(
    ([value, label]) => ({ value: value as HierarchicalLevel, label })
  );

  readonly statusOptions = Object.entries(STATUS_LABELS).map(
    ([value, label]) => ({ value: value as StatusFuncionario, label })
  );

  get isEditMode(): boolean {
    return !!this.data?.funcionario;
  }

  ngOnInit(): void {
    const f = this.data?.funcionario;
    this.form = this.fb.group({
      nomeFuncionario: [f?.nomeFuncionario ?? '', Validators.required],
      cpf:             [f?.cpf ?? '',             [Validators.required, Validators.pattern(/^\d{3}\.\d{3}\.\d{3}-\d{2}$/)]],
      email:           [f?.email ?? '',           [Validators.required, Validators.email]],
      telefonePrimario:[f?.telefonePrimario ?? '', [Validators.required, Validators.pattern(/\(\d{2}\) 9\d{4}-\d{4}/)]],
      telefoneSegundario:[f?.telefoneSegundario ?? '', Validators.pattern(/(\(\d{2}\) 9\d{4}-\d{4})?/)],
      cargo:           [f?.cargo ?? '',           Validators.required],
      nivelHierarquico:[f?.nivelHierarquico ?? null, Validators.required],
      statusFuncionario:[f?.statusFuncionario ?? null, Validators.required],
    });
  }

  onSubmit(): void {
    if (this.form.invalid) return;
    this.dialogRef.close(this.form.value);
  }
}

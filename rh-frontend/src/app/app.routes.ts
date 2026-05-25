// src/app/app.routes.ts
import { Routes } from '@angular/router';

export const routes: Routes = [
  {
    path: '',
    redirectTo: 'funcionarios',
    pathMatch: 'full',
  },
  {
    path: 'funcionarios',
    loadComponent: () =>
      import('./features/funcionarios/lista/funcionarios-lista.component').then(
        (m) => m.FuncionariosListaComponent
      ),
    title: 'Funcionários — Sistema RH',
  },
  {
    path: '**',
    redirectTo: 'funcionarios',
  },
];

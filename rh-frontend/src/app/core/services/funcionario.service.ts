// src/app/core/services/funcionario.service.ts
import { Injectable, inject } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import {
  FuncionarioRequest,
  FuncionarioResponse,
  Page,
} from '../models/funcionario.model';

@Injectable({ providedIn: 'root' })
export class FuncionarioService {
  private readonly http = inject(HttpClient);
  private readonly baseUrl = 'http://localhost:8080/api/funcionarios';

  /** Lista funcionários paginados */
  getAll(page = 0, size = 10, sort = 'nomeFuncionario'): Observable<Page<FuncionarioResponse>> {
    const params = new HttpParams()
      .set('page', page)
      .set('size', size)
      .set('sort', sort);
    return this.http.get<Page<FuncionarioResponse>>(this.baseUrl, { params });
  }

  /** Busca um funcionário por ID */
  getById(id: number): Observable<FuncionarioResponse> {
    return this.http.get<FuncionarioResponse>(`${this.baseUrl}/${id}`);
  }

  /** Cadastra um novo funcionário */
  create(request: FuncionarioRequest): Observable<FuncionarioResponse> {
    return this.http.post<FuncionarioResponse>(this.baseUrl, request);
  }

  /** Atualiza os dados de um funcionário */
  update(id: number, request: FuncionarioRequest): Observable<FuncionarioResponse> {
    return this.http.put<FuncionarioResponse>(`${this.baseUrl}/${id}`, request);
  }

  /** Remove um funcionário */
  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/${id}`);
  }
}

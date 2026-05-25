// src/app/core/models/funcionario.model.ts

export type HierarchicalLevel =
  | 'JOVEM_APRENDIZ'
  | 'ESTAGIARIO'
  | 'ASSISTENTE'
  | 'JUNIOR'
  | 'PLENO'
  | 'SENIOR'
  | 'ESPECIALISTA'
  | 'LIDER_EQUIPE'
  | 'GERENTE'
  | 'DIRETOR'
  | 'CEO';

export type StatusFuncionario = 'ATIVO' | 'INATIVO' | 'FERIAS' | 'AFASTADO';

/** DTO de resposta (retorno da API) */
export interface FuncionarioResponse {
  id: number;
  nomeFuncionario: string;
  cpf: string;
  email: string;
  telefonePrimario: string;
  telefoneSegundario?: string;
  cargo: string;
  nivelHierarquico: HierarchicalLevel;
  statusFuncionario: StatusFuncionario;
}

/** DTO de requisição (envio para a API) */
export interface FuncionarioRequest {
  nomeFuncionario: string;
  cpf: string;
  email: string;
  telefonePrimario: string;
  telefoneSegundario?: string;
  cargo: string;
  nivelHierarquico: HierarchicalLevel;
  statusFuncionario: StatusFuncionario;
}

/** Resposta paginada do Spring Data */
export interface Page<T> {
  content: T[];
  totalElements: number;
  totalPages: number;
  size: number;
  number: number;
  first: boolean;
  last: boolean;
}

/** Labels de exibição para os enums */
export const HIERARCHICAL_LEVEL_LABELS: Record<HierarchicalLevel, string> = {
  JOVEM_APRENDIZ: 'Jovem Aprendiz',
  ESTAGIARIO: 'Estagiário',
  ASSISTENTE: 'Assistente',
  JUNIOR: 'Júnior',
  PLENO: 'Pleno',
  SENIOR: 'Sênior',
  ESPECIALISTA: 'Especialista',
  LIDER_EQUIPE: 'Líder de Equipe',
  GERENTE: 'Gerente',
  DIRETOR: 'Diretor',
  CEO: 'CEO',
};

export const STATUS_LABELS: Record<StatusFuncionario, string> = {
  ATIVO: 'Ativo',
  INATIVO: 'Inativo',
  FERIAS: 'Férias',
  AFASTADO: 'Afastado',
};

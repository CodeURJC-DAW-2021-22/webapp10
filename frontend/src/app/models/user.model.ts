export enum UserRole {
  USER = 'USER',
  ADMIN = 'ADMIN',
  TEACHER = 'TEACHER',
}

export interface User {
  id?: number;
  email: string;
  firstName: string;
  lastName: string;
  encodedPassword?: string;
  roles: UserRole[];
}

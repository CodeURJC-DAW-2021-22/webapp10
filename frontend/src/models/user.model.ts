export interface User {
    id?: number;
    email: string;
    firstName: string;
    lastName: string;
    encodedPassword: string;
    roles: string[];
}
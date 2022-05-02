export interface Order {
  id?: number;
  price: number;
  course: number;
  courseTitle: string;
  user: number;
  userName: string;
  paymentMethod: string;
  billingAddress: string;
  country: string;
  region: string;
  dataCard: string;
  date: string;
}

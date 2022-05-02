import { User } from './user.model';

export interface Lesson {
  id?: number;
  title: string;
  description: string;
  videoUrl: string;
  imageId: number;
  author: User;
}

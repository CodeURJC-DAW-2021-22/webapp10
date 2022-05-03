import { Lesson } from './lesson.model';
import { User } from './user.model';

export interface Course {
  id?: number;
  title: string;
  description: string;
  price: number;
  tags: string[];
  lessons: Lesson[];
  author: User;
}

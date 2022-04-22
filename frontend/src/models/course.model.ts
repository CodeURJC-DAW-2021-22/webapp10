import { Lesson } from './lesson.model';

export interface Course {
  id?: number;
  title: string;
  description: string;
  price: number;
  tags: string[];
  lessons: Lesson[];
}

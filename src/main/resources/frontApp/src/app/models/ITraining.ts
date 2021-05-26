interface IExercise {
  id: number;
  difficulty: string;
  name: string;
  description: string;
  equipments: string;
}

interface ISeries {
  id: number;
  trainingUnit: number;
  weight: number;
  reps: number;
  unit: string;
}

export interface IUnit {
  id: number;
  exercise: IExercise;
  series: ISeries[];
}

export interface ITraining {
  id: number;
  difficulty: string;
  date: string;
  title: string;
  training: string;
  unit: IUnit[];
}

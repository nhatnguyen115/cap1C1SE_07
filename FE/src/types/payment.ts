export type MembershipPlan = {
  id: number;
  planName: string;
  description: string;
  price: number;
  durationDays: number;
};

export type UserMembership = {
  planName: string;
  price: number;
  durationDays: number;
  startDate?: string;
  status?: string;
}

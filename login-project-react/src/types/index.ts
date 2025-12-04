import { Film } from "../service/FilmsService";

export interface loginResponse {
  token?: string;
  username?: string;
  roles?: string[];
  permissions?: string[];
  films?: Film[];
}

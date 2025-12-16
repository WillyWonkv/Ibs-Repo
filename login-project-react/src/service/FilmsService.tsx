import axios from "axios";
import api from "./AxiosSettings"

export interface Film{
    id:number;
    coverSrc:string;
    title:string;
    description:string;
    duration:number;
    genres:Genre[];
    cover?: File;
}

export interface Genre{
    id:number;
    name:string;
}

export interface PageResponse<T> {
  content: T[];
  totalPages: number;
  totalElements: number;
  number: number; 
  size: number;   
}

export const handleGetAllGenresService = async (): Promise<Genre[]> => {
    try{
        const resp = await axios.get<Genre[]>("http://localhost:8080/genre");
        return resp.data;
    }catch(err){
        console.error(err);
        throw err;
    }   
}

export const handleGetAllFilmsService = async (): Promise<Film[]> => {
    try{
        const resp = await axios.get<Film[]>("http://localhost:8080/film");
        return resp.data;
    }catch(err){
        console.error(err);
        throw err
    }
}

export const handleGetAllFilmsPageService = async (curretPage: number, size: number): Promise<PageResponse<Film>> => {
    try {
        const resp = await axios.get<PageResponse<Film>>("http://localhost:8080/film/page", {
            params: {
                page: curretPage - 1,
                size: size
            }
        });
        return resp.data;
    }catch(err){
        console.error(err);
        throw err
    }

}

export const handleGetFilmsByGenrePageService = async (id: number, curretPage: number, size: number): Promise<PageResponse<Film>> => {

    try {
        const resp = await axios.get<PageResponse<Film>>(`http://localhost:8080/film/genre/page/${id}`, {
            params: {
                page: curretPage - 1,
                size: size
            }
        })
        return resp.data;
    }catch(err){
        console.error(err);
        throw err;
    }

}

export const handleDeleteFilmService = async (id: number): Promise<boolean> => {
    try {
        await api.delete(`/film/${id}`);
        return true;
    } catch (err) {
        console.error(err);
        throw err
    }
};

export const handleUpdateFilmService = async (film: Film) => {
    try {
        const formData = new FormData();
        formData.append("film", new Blob([JSON.stringify(film)], { type: "application/json" }));
        
        if(film.cover){
            formData.append("file", film.cover);
        }
       
        await api.put(`/film`, formData)
  
    } catch (err) {
        console.error(err);
        throw err
    }
}

export const handleCreateFilmService = async (film: Film) => {
    try {
        const formData = new FormData();
        formData.append("film", new Blob([JSON.stringify(film)], {type: "application/json"}));

        if(film.cover){
            formData.append("file", film.cover)
        }

        await api.post(`/film`, formData)
    } catch (err) {
        console.error(err);
        throw err
    }
}

export const handleGetFilmByGenreService = async (id: number): Promise<Film[]> => {
    try{
        const resp = await axios.get<Film[]>(`http://localhost:8080/film/genre/${id}`);
        return resp.data;
    }catch(err){
        console.error(err);
        return [];
    }
}

export const handleGetFilmByTitleService = async (title: string): Promise<Film[]> => {
    try{
        const resp = await axios.get<Film[]>(`http://localhost:8080/film/search`,{
            params: { title }
        });
        return resp.data;
    }catch(err){
        console.error(err);
        throw err
    }
}


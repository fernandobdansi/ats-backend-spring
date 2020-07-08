--
-- PostgreSQL database dump
--

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: cliente; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE public.cliente (
    id integer NOT NULL,
    cpf character varying(13),
    endereco character varying(100),
    nome character varying(50),
    telefone character varying(12)
);


ALTER TABLE public.cliente OWNER TO postgres;

--
-- Name: cliente_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.cliente_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.cliente_id_seq OWNER TO postgres;

--
-- Name: cliente_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.cliente_id_seq OWNED BY public.cliente.id;


--
-- Name: dispositivo; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE public.dispositivo (
    id integer NOT NULL,
    descricao character varying(150),
    num_serie integer,
    cliente_id integer,
    marca_id integer,
    modelo_id integer
);


ALTER TABLE public.dispositivo OWNER TO postgres;

--
-- Name: dispositivo_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.dispositivo_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.dispositivo_id_seq OWNER TO postgres;

--
-- Name: dispositivo_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.dispositivo_id_seq OWNED BY public.dispositivo.id;


--
-- Name: feed_back; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE public.feed_back (
    id integer NOT NULL,
    comentario character varying(100),
    satisfacao integer,
    ordemdeservico_id integer
);


ALTER TABLE public.feed_back OWNER TO postgres;

--
-- Name: feed_back_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.feed_back_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.feed_back_id_seq OWNER TO postgres;

--
-- Name: feed_back_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.feed_back_id_seq OWNED BY public.feed_back.id;


--
-- Name: garantia; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE public.garantia (
    id integer NOT NULL,
    data_entrada character varying(8),
    data_saida character varying(8),
    motivo character varying(150),
    ordemdeservico_id integer,
    servico_id integer,
    statusgarantia_id integer
);


ALTER TABLE public.garantia OWNER TO postgres;

--
-- Name: garantia_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.garantia_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.garantia_id_seq OWNER TO postgres;

--
-- Name: garantia_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.garantia_id_seq OWNED BY public.garantia.id;


--
-- Name: marca; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE public.marca (
    id integer NOT NULL,
    nome character varying(20)
);


ALTER TABLE public.marca OWNER TO postgres;

--
-- Name: marca_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.marca_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.marca_id_seq OWNER TO postgres;

--
-- Name: marca_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.marca_id_seq OWNED BY public.marca.id;


--
-- Name: modelo; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE public.modelo (
    id integer NOT NULL,
    nome character varying(20),
    marca_id integer
);


ALTER TABLE public.modelo OWNER TO postgres;

--
-- Name: modelo_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.modelo_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.modelo_id_seq OWNER TO postgres;

--
-- Name: modelo_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.modelo_id_seq OWNED BY public.modelo.id;


--
-- Name: orcamento; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE public.orcamento (
    id integer NOT NULL,
    descricao character varying(150),
    valor_total real,
    cliente_id integer,
    dispositivo_id integer,
    tecnico_id integer
);


ALTER TABLE public.orcamento OWNER TO postgres;

--
-- Name: orcamento_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.orcamento_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.orcamento_id_seq OWNER TO postgres;

--
-- Name: orcamento_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.orcamento_id_seq OWNED BY public.orcamento.id;


--
-- Name: ordem_de_servico; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE public.ordem_de_servico (
    id integer NOT NULL,
    data_entrada character varying(8),
    data_saida character varying(8),
    descricao_problema character varying(50),
    valor_total real,
    cliente_id integer,
    dispositivo_id integer,
    statusordemdeservico_id integer,
    tecnico_id integer
);


ALTER TABLE public.ordem_de_servico OWNER TO postgres;

--
-- Name: ordem_de_servico_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.ordem_de_servico_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.ordem_de_servico_id_seq OWNER TO postgres;

--
-- Name: ordem_de_servico_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.ordem_de_servico_id_seq OWNED BY public.ordem_de_servico.id;


--
-- Name: ordem_servico_item; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE public.ordem_servico_item (
    valor real,
    ordemdeservico_id integer NOT NULL,
    servicos_id integer NOT NULL
);


ALTER TABLE public.ordem_servico_item OWNER TO postgres;

--
-- Name: servicos; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE public.servicos (
    id integer NOT NULL,
    descricao character varying(100)
);


ALTER TABLE public.servicos OWNER TO postgres;

--
-- Name: servicos_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.servicos_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.servicos_id_seq OWNER TO postgres;

--
-- Name: servicos_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.servicos_id_seq OWNED BY public.servicos.id;


--
-- Name: status_garantia; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE public.status_garantia (
    id integer NOT NULL,
    descricao character varying(100)
);


ALTER TABLE public.status_garantia OWNER TO postgres;

--
-- Name: status_garantia_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.status_garantia_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.status_garantia_id_seq OWNER TO postgres;

--
-- Name: status_garantia_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.status_garantia_id_seq OWNED BY public.status_garantia.id;


--
-- Name: status_ordem_de_servico; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE public.status_ordem_de_servico (
    id integer NOT NULL,
    descricao character varying(100)
);


ALTER TABLE public.status_ordem_de_servico OWNER TO postgres;

--
-- Name: status_ordem_de_servico_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.status_ordem_de_servico_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.status_ordem_de_servico_id_seq OWNER TO postgres;

--
-- Name: status_ordem_de_servico_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.status_ordem_de_servico_id_seq OWNED BY public.status_ordem_de_servico.id;


--
-- Name: tecnico; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE public.tecnico (
    id integer NOT NULL,
    cpf character varying(13),
    "endereço" character varying(200),
    nome character varying(50),
    telefone character varying(12)
);


ALTER TABLE public.tecnico OWNER TO postgres;

--
-- Name: tecnico_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.tecnico_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.tecnico_id_seq OWNER TO postgres;

--
-- Name: tecnico_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.tecnico_id_seq OWNED BY public.tecnico.id;


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cliente ALTER COLUMN id SET DEFAULT nextval('public.cliente_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.dispositivo ALTER COLUMN id SET DEFAULT nextval('public.dispositivo_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.feed_back ALTER COLUMN id SET DEFAULT nextval('public.feed_back_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.garantia ALTER COLUMN id SET DEFAULT nextval('public.garantia_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.marca ALTER COLUMN id SET DEFAULT nextval('public.marca_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.modelo ALTER COLUMN id SET DEFAULT nextval('public.modelo_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.orcamento ALTER COLUMN id SET DEFAULT nextval('public.orcamento_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ordem_de_servico ALTER COLUMN id SET DEFAULT nextval('public.ordem_de_servico_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.servicos ALTER COLUMN id SET DEFAULT nextval('public.servicos_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.status_garantia ALTER COLUMN id SET DEFAULT nextval('public.status_garantia_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.status_ordem_de_servico ALTER COLUMN id SET DEFAULT nextval('public.status_ordem_de_servico_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tecnico ALTER COLUMN id SET DEFAULT nextval('public.tecnico_id_seq'::regclass);


--
-- Name: cliente_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY public.cliente
    ADD CONSTRAINT cliente_pkey PRIMARY KEY (id);


--
-- Name: dispositivo_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY public.dispositivo
    ADD CONSTRAINT dispositivo_pkey PRIMARY KEY (id);


--
-- Name: feed_back_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY public.feed_back
    ADD CONSTRAINT feed_back_pkey PRIMARY KEY (id);


--
-- Name: garantia_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY public.garantia
    ADD CONSTRAINT garantia_pkey PRIMARY KEY (id);


--
-- Name: marca_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY public.marca
    ADD CONSTRAINT marca_pkey PRIMARY KEY (id);


--
-- Name: modelo_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY public.modelo
    ADD CONSTRAINT modelo_pkey PRIMARY KEY (id);


--
-- Name: orcamento_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY public.orcamento
    ADD CONSTRAINT orcamento_pkey PRIMARY KEY (id);


--
-- Name: ordem_de_servico_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY public.ordem_de_servico
    ADD CONSTRAINT ordem_de_servico_pkey PRIMARY KEY (id);


--
-- Name: ordem_servico_item_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY public.ordem_servico_item
    ADD CONSTRAINT ordem_servico_item_pkey PRIMARY KEY (ordemdeservico_id, servicos_id);


--
-- Name: servicos_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY public.servicos
    ADD CONSTRAINT servicos_pkey PRIMARY KEY (id);


--
-- Name: status_garantia_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY public.status_garantia
    ADD CONSTRAINT status_garantia_pkey PRIMARY KEY (id);


--
-- Name: status_ordem_de_servico_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY public.status_ordem_de_servico
    ADD CONSTRAINT status_ordem_de_servico_pkey PRIMARY KEY (id);


--
-- Name: tecnico_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY public.tecnico
    ADD CONSTRAINT tecnico_pkey PRIMARY KEY (id);


--
-- Name: fk3hit98r8muhgwn9eej356bg82; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.dispositivo
    ADD CONSTRAINT fk3hit98r8muhgwn9eej356bg82 FOREIGN KEY (modelo_id) REFERENCES public.modelo(id);


--
-- Name: fk55tqxpcj427d13xwxsl5vekii; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.garantia
    ADD CONSTRAINT fk55tqxpcj427d13xwxsl5vekii FOREIGN KEY (statusgarantia_id) REFERENCES public.status_garantia(id);


--
-- Name: fk5j1e6r18aoxxlpnpldwfcx6jr; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.garantia
    ADD CONSTRAINT fk5j1e6r18aoxxlpnpldwfcx6jr FOREIGN KEY (ordemdeservico_id, servico_id) REFERENCES public.ordem_servico_item(ordemdeservico_id, servicos_id);


--
-- Name: fkakia5w376xif20w2auwe351en; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ordem_de_servico
    ADD CONSTRAINT fkakia5w376xif20w2auwe351en FOREIGN KEY (statusordemdeservico_id) REFERENCES public.status_ordem_de_servico(id);


--
-- Name: fkam43o350hfbjaaf5ddo0hjghf; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ordem_servico_item
    ADD CONSTRAINT fkam43o350hfbjaaf5ddo0hjghf FOREIGN KEY (servicos_id) REFERENCES public.servicos(id);


--
-- Name: fkb9gv80090sehtxoqo5xstnfiv; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.orcamento
    ADD CONSTRAINT fkb9gv80090sehtxoqo5xstnfiv FOREIGN KEY (cliente_id) REFERENCES public.cliente(id);


--
-- Name: fkb9oigg8h2kylbsiaylf2pkhr0; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ordem_servico_item
    ADD CONSTRAINT fkb9oigg8h2kylbsiaylf2pkhr0 FOREIGN KEY (ordemdeservico_id) REFERENCES public.ordem_de_servico(id);


--
-- Name: fkcxnjhtj47i223ns4vsphcbqnq; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.dispositivo
    ADD CONSTRAINT fkcxnjhtj47i223ns4vsphcbqnq FOREIGN KEY (cliente_id) REFERENCES public.cliente(id);


--
-- Name: fkfl0gjtsbgy5po7ewxcyeik0pr; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.feed_back
    ADD CONSTRAINT fkfl0gjtsbgy5po7ewxcyeik0pr FOREIGN KEY (ordemdeservico_id) REFERENCES public.ordem_de_servico(id);


--
-- Name: fkj2rkkaxbthrm0jsta7i81bajq; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.orcamento
    ADD CONSTRAINT fkj2rkkaxbthrm0jsta7i81bajq FOREIGN KEY (tecnico_id) REFERENCES public.tecnico(id);


--
-- Name: fkllxq2dldvhxvb5q9csar7vdfy; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.modelo
    ADD CONSTRAINT fkllxq2dldvhxvb5q9csar7vdfy FOREIGN KEY (marca_id) REFERENCES public.marca(id);


--
-- Name: fkofaemrifs5m3mv8dsxdr55cde; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ordem_de_servico
    ADD CONSTRAINT fkofaemrifs5m3mv8dsxdr55cde FOREIGN KEY (cliente_id) REFERENCES public.cliente(id);


--
-- Name: fkonxx4eckftsmkdg3c4fp0r0ax; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.dispositivo
    ADD CONSTRAINT fkonxx4eckftsmkdg3c4fp0r0ax FOREIGN KEY (marca_id) REFERENCES public.marca(id);


--
-- Name: fkounhni5rfmw3qeuswgp2c129r; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ordem_de_servico
    ADD CONSTRAINT fkounhni5rfmw3qeuswgp2c129r FOREIGN KEY (dispositivo_id) REFERENCES public.dispositivo(id);


--
-- Name: fkpmxnkacre2d150cgvkqqq8p6p; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.orcamento
    ADD CONSTRAINT fkpmxnkacre2d150cgvkqqq8p6p FOREIGN KEY (dispositivo_id) REFERENCES public.dispositivo(id);


--
-- Name: fktpm6aspie95r37n22tbayjkkm; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ordem_de_servico
    ADD CONSTRAINT fktpm6aspie95r37n22tbayjkkm FOREIGN KEY (tecnico_id) REFERENCES public.tecnico(id);


--
-- Name: SCHEMA public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- PostgreSQL database dump complete
--


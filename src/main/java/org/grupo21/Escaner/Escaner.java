/* Generated By:JavaCC: Do not edit this line. Escaner.java */
package org.grupo21.Escaner;

import org.grupo21.Repositories.IndicadoresRepo;
import org.grupo21.Model.*;
import org.grupo21.Model.Interfaces.*;

public class Escaner implements EscanerConstants {
	private IndicadoresRepo _context = null;

	public void setContext(IndicadoresRepo p_context) {
		this._context = p_context;
	}

	final public IndicadorInterface parsear() throws ParseException {
		return new Indicador(nombre(), asignacion());
	}

	final public CondicionInterface parsearCondicion() throws ParseException {
		return condicion();
	}

	final public IndicadorInterface expr() throws ParseException {
		IndicadorInterface a;
		IndicadorInterface result = null;
		a = term();
		label_1: while (true) {
			switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
			case ADD:
			case SUB:
				;
				break;
			default:
				jj_la1[0] = jj_gen;
				break label_1;
			}
			switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
			case ADD:
				jj_consume_token(ADD);
				result = new Suma(a, expr());
				break;
			case SUB:
				jj_consume_token(SUB);
				result = new Resta(a, expr());
				break;
			default:
				jj_la1[1] = jj_gen;
				jj_consume_token(-1);
				throw new ParseException();
			}
		}
		if (result != null) {
			return result;
		}

		return a;
	}

	final public IndicadorInterface term() throws ParseException {
		IndicadorInterface a;
		IndicadorInterface result = null;
		a = factor();
		label_2: while (true) {
			switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
			case MUL:
			case DIV:
				;
				break;
			default:
				jj_la1[2] = jj_gen;
				break label_2;
			}
			switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
			case MUL:
				jj_consume_token(MUL);
				result = new Multiplicacion(a, term());
				break;
			case DIV:
				jj_consume_token(DIV);
				result = new Division(a, term());
				break;
			default:
				jj_la1[3] = jj_gen;
				jj_consume_token(-1);
				throw new ParseException();
			}
		}
		if (result != null) {
			return result;
		}
		return a;
	}

	final public IndicadorInterface factor() throws ParseException {
		Token t;
		IndicadorInterface result = null;
		switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
		case INVERSION:
			t = jj_consume_token(INVERSION);
			return new ParametroCuenta(t.image);
		case INGRESOSCONT:
			t = jj_consume_token(INGRESOSCONT);
			return new ParametroCuenta(t.image);
		case INGRESOSDISCONT:
			t = jj_consume_token(INGRESOSDISCONT);
			return new ParametroCuenta(t.image);
		case DIVIDENDOS:
			t = jj_consume_token(DIVIDENDOS);
			return new ParametroCuenta(t.image);
		case DEUDA:
			t = jj_consume_token(DEUDA);
			return new ParametroCuenta(t.image);
		case MARGENVENTA:
			t = jj_consume_token(MARGENVENTA);
			return new ParametroCuenta(t.image);
		case ROA:
			t = jj_consume_token(ROA);
			return new ParametroCuenta(t.image);
		case TIR:
			t = jj_consume_token(TIR);
			return new ParametroCuenta(t.image);
		case RECUPEROINV:
			t = jj_consume_token(RECUPEROINV);
			return new ParametroCuenta(t.image);
		case CAPITAL:
			t = jj_consume_token(CAPITAL);
			return new ParametroCuenta(t.image);
		case INT:
			t = jj_consume_token(INT);
			return new Constante(Integer.parseInt(t.image));
		case LBR:
			jj_consume_token(LBR);
			result = expr();
			jj_consume_token(RBR);
			return result;
		case INDICADOR:
			t = jj_consume_token(INDICADOR);
			return _context.obtenerIndicadorSegunNombre(t.image);
		case 0:
			jj_consume_token(0);
			return null;
		default:
			jj_la1[4] = jj_gen;
			jj_consume_token(-1);
			throw new ParseException();
		}
	}

	final public String nombre() throws ParseException {
		Token t;
		t = jj_consume_token(INDICADOR);
		return t.image;
	}

	final public IndicadorInterface asignacion() throws ParseException {
		IndicadorInterface a;
		jj_consume_token(IGU);

		a = expr();
		return a;
	}

	@SuppressWarnings("unused")
	final public CondicionInterface condicion() throws ParseException {
		Token t = null;
		IndicadorInterface expresionPrefija = expr();
		CondicionInterface result;
		switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
		case LOWER:
			t = jj_consume_token(LOWER);
			result = new CondicionMenor(expresionPrefija, expr());
			break;
		case LOWEREQUAL:
			t = jj_consume_token(LOWEREQUAL);
			result = new CondicionMenorIgual(expresionPrefija, expr());
			break;
		case GREATER:
			t = jj_consume_token(GREATER);
			result = new CondicionMayor(expresionPrefija, expr());
			break;
		case GREATEREQUAL:
			t = jj_consume_token(GREATEREQUAL);
			result = new CondicionMayorIgual(expresionPrefija, expr());
			break;
		case EQUAL:
			t = jj_consume_token(EQUAL);
			result = new CondicionIgual(expresionPrefija, expr());
			break;
		default:
			jj_la1[5] = jj_gen;
			jj_consume_token(-1);
			throw new ParseException();
		}
		return result;
	}

	/** Generated Token Manager. */
	public EscanerTokenManager token_source;
	SimpleCharStream jj_input_stream;
	/** Current token. */
	public Token token;
	/** Next token. */
	public Token jj_nt;
	private int jj_ntk;
	private int jj_gen;
	final private int[] jj_la1 = new int[6];
	static private int[] jj_la1_0;
	static {
		jj_la1_init_0();
	}

	private static void jj_la1_init_0() {
		jj_la1_0 = new int[] { 0x60, 0x60, 0x180, 0x180, 0xfff401, 0x1f000000, };
	}

	/** Constructor with InputStream. */
	public Escaner(java.io.InputStream stream) {
		this(stream, null);
	}

	/** Constructor with InputStream and supplied encoding */
	public Escaner(java.io.InputStream stream, String encoding) {
		try {
			jj_input_stream = new SimpleCharStream(stream, encoding, 1, 1);
		} catch (java.io.UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
		token_source = new EscanerTokenManager(jj_input_stream);
		token = new Token();
		jj_ntk = -1;
		jj_gen = 0;
		for (int i = 0; i < 6; i++)
			jj_la1[i] = -1;
	}

	/** Reinitialise. */
	public void ReInit(java.io.InputStream stream) {
		ReInit(stream, null);
	}

	/** Reinitialise. */
	public void ReInit(java.io.InputStream stream, String encoding) {
		try {
			jj_input_stream.ReInit(stream, encoding, 1, 1);
		} catch (java.io.UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
		token_source.ReInit(jj_input_stream);
		token = new Token();
		jj_ntk = -1;
		jj_gen = 0;
		for (int i = 0; i < 6; i++)
			jj_la1[i] = -1;
	}

	/** Constructor. */
	public Escaner(java.io.Reader stream) {
		jj_input_stream = new SimpleCharStream(stream, 1, 1);
		token_source = new EscanerTokenManager(jj_input_stream);
		token = new Token();
		jj_ntk = -1;
		jj_gen = 0;
		for (int i = 0; i < 6; i++)
			jj_la1[i] = -1;
	}

	/** Reinitialise. */
	public void ReInit(java.io.Reader stream) {
		jj_input_stream.ReInit(stream, 1, 1);
		token_source.ReInit(jj_input_stream);
		token = new Token();
		jj_ntk = -1;
		jj_gen = 0;
		for (int i = 0; i < 6; i++)
			jj_la1[i] = -1;
	}

	/** Constructor with generated Token Manager. */
	public Escaner(EscanerTokenManager tm) {
		token_source = tm;
		token = new Token();
		jj_ntk = -1;
		jj_gen = 0;
		for (int i = 0; i < 6; i++)
			jj_la1[i] = -1;
	}

	/** Reinitialise. */
	public void ReInit(EscanerTokenManager tm) {
		token_source = tm;
		token = new Token();
		jj_ntk = -1;
		jj_gen = 0;
		for (int i = 0; i < 6; i++)
			jj_la1[i] = -1;
	}

	private Token jj_consume_token(int kind) throws ParseException {
		Token oldToken;
		if ((oldToken = token).next != null)
			token = token.next;
		else
			token = token.next = token_source.getNextToken();
		jj_ntk = -1;
		if (token.kind == kind) {
			jj_gen++;
			return token;
		}
		token = oldToken;
		jj_kind = kind;
		throw generateParseException();
	}

	/** Get the next Token. */
	final public Token getNextToken() {
		if (token.next != null)
			token = token.next;
		else
			token = token.next = token_source.getNextToken();
		jj_ntk = -1;
		jj_gen++;
		return token;
	}

	/** Get the specific Token. */
	final public Token getToken(int index) {
		Token t = token;
		for (int i = 0; i < index; i++) {
			if (t.next != null)
				t = t.next;
			else
				t = t.next = token_source.getNextToken();
		}
		return t;
	}

	private int jj_ntk() {
		if ((jj_nt = token.next) == null)
			return (jj_ntk = (token.next = token_source.getNextToken()).kind);
		else
			return (jj_ntk = jj_nt.kind);
	}

	private java.util.List<int[]> jj_expentries = new java.util.ArrayList<int[]>();
	private int[] jj_expentry;
	private int jj_kind = -1;

	/** Generate ParseException. */
	public ParseException generateParseException() {
		jj_expentries.clear();
		boolean[] la1tokens = new boolean[29];
		if (jj_kind >= 0) {
			la1tokens[jj_kind] = true;
			jj_kind = -1;
		}
		for (int i = 0; i < 6; i++) {
			if (jj_la1[i] == jj_gen) {
				for (int j = 0; j < 32; j++) {
					if ((jj_la1_0[i] & (1 << j)) != 0) {
						la1tokens[j] = true;
					}
				}
			}
		}
		for (int i = 0; i < 29; i++) {
			if (la1tokens[i]) {
				jj_expentry = new int[1];
				jj_expentry[0] = i;
				jj_expentries.add(jj_expentry);
			}
		}
		int[][] exptokseq = new int[jj_expentries.size()][];
		for (int i = 0; i < jj_expentries.size(); i++) {
			exptokseq[i] = jj_expentries.get(i);
		}
		return new ParseException(token, exptokseq, tokenImage);
	}

	/** Enable tracing. */
	final public void enable_tracing() {
	}

	/** Disable tracing. */
	final public void disable_tracing() {
	}

}

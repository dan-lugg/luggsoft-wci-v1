package com.luggsoft.wci.core.search

import com.luggsoft.wci.core.Transmittable
import com.github.h0tk3y.betterParse.combinators.or
import com.github.h0tk3y.betterParse.grammar.Grammar
import com.github.h0tk3y.betterParse.grammar.parseToEnd
import com.github.h0tk3y.betterParse.lexer.literalToken
import com.github.h0tk3y.betterParse.lexer.regexToken
import com.github.h0tk3y.betterParse.lexer.token
import com.github.h0tk3y.betterParse.parser.Parser

interface Criteria

class EmptyCriteria : Criteria

data class ExpressionCriteria(
    val expression: Expression,
) : Criteria

interface Expression : Transmittable

///
///
///

interface CriteriaTransformer<TResult>
{
    fun transformCriteria(criteria: Criteria): TResult
}

abstract class CriteriaTransformerBase<TResult> : CriteriaTransformer<TResult>
{
    protected abstract val expressionTransformers: List<ExpressionTransformer<*>>
    
    protected fun <TExpressionResult> transformExpression(expression: Expression): TExpressionResult
    {
        TODO()
    }
    
}

interface ExpressionTransformer<TResult>
{
    fun canTransform(expression: Expression): Boolean
    fun transformExpression(expression: Expression): TResult
}

///
///
///

/**
 *
 * foo
 *   -> searches for "foo" in any field
 *
 * foo bar
 *   -> searches for "foo" or "bar" in any field
 *
 * "foo bar"
 *   -> searches for "foo bar" in any field
 *
 * qux:foo
 *   -> searches for "foo" in the <qux> field
 *
 * qux:"foo bar"
 *   -> searches for "foo bar" in the <qux> field
 *
 */

///
///
///

interface CriteriaParser
{
    fun parseSearch(input: String): Criteria
}

class BetterParseCriteriaParser : CriteriaParser
{
    private val betterParseCriteriaGrammar = BetterParseCriteriaGrammar()
    
    override fun parseSearch(input: String): Criteria
    {
        return this.betterParseCriteriaGrammar.parseToEnd(input)
    }
}

class BetterParseCriteriaGrammar : Grammar<Criteria>()
{
    val identifier by regexToken(pattern = "\\w+")
    
    val trueBooleanToken by literalToken(text = "true")
    val falseBooleanToken by literalToken(text = "false")
    val booleanToken by this.trueBooleanToken or this.falseBooleanToken
    
    val numberToken by regexToken("[+-](0|1-9][0-9]*)(\\.(0|[1-9][0-9]*))?")
    
    val stringToken by token { charSequence, index ->
        TODO("Implement string matching")
    }
    
    override val rootParser: Parser<Criteria>
        get() = TODO("Not yet implemented")
}

///
///
///

interface EntityProvider<TEntity>
{
    fun findFirst(criteria: Criteria): TEntity
    
    fun findEvery(criteria: Criteria): List<TEntity>
}


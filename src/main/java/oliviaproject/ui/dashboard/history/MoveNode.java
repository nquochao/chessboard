/*
 * Copyright (c) 1995, 2008, Oracle and/or its affiliates. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 *   - Neither the name of Oracle or the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package oliviaproject.ui.dashboard.history;

import java.util.Vector;

import oliviaproject.chessboard.pgn.GameStateMutable;

public class MoveNode {
	MoveNode father;
	Vector<MoveNode> children;
	private String name;
	GameStateMutable gameStateMutable;

	public GameStateMutable getGameStateMutable() {
		return gameStateMutable;
	}

	public void setGameStateMutable(GameStateMutable gameStateMutable) {
		this.gameStateMutable = gameStateMutable;
	}

	public MoveNode(String name, GameStateMutable gameStateMutable) {
		this.gameStateMutable = gameStateMutable;
		this.name = name;
		father = null;
		children = new Vector<MoveNode>();
	}

	/**
	 * Link together all members of a family.
	 *
	 * @param pa   the father
	 * @param ma   the mother
	 * @param kids the children
	 */
	public static void linkFamily(MoveNode pa,

			MoveNode[] kids) {
		for (MoveNode kid : kids) {
			pa.children.addElement(kid);
			kid.father = pa;
		}
	}

/// getter methods ///////////////////////////////////

	public String toString() {
		return name;
	}

	public String getName() {
		return name;
	}

	public MoveNode getFather() {
		return father;
	}

	public int getChildCount() {
		return children.size();
	}

	public MoveNode getChildAt(int i) {
		return (MoveNode) children.elementAt(i);
	}

	public int getIndexOfChild(MoveNode kid) {
		return children.indexOf(kid);
	}
}

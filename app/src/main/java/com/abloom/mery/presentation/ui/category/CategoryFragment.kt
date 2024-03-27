package com.abloom.mery.presentation.ui.category

import android.graphics.Paint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.abloom.mery.R
import com.abloom.mery.categorytest.CategoryViewModel
import com.abloom.mery.categorytest.Question
import com.abloom.mery.databinding.FragmentCategoryBinding
import com.abloom.mery.presentation.common.base.BaseFragment
import com.abloom.mery.presentation.common.view.setOnNavigationClick
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CategoryFragment : BaseFragment<FragmentCategoryBinding>(R.layout.fragment_category),
    CategoryRecyclerListener {

    private lateinit var categoryAdapter: CategoryAdapter
    private val viewModel: CategoryViewModel by viewModels()
    private val args: CategoryFragmentArgs by navArgs()
    private var isLogin = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.appbarCategory.setOnNavigationClick {
            findNavController().popBackStack()
        }
        viewModel.isCheckLogin()
        setupIsLogin()
        setCategoryAdapter(isLogin)
        observeCategory()
        setupSelectedTabItem(args.category.categoryName)
        initListener()
    }

    private fun setupIsLogin() {
        viewModel.isLogin.observe(viewLifecycleOwner) {
            when (it) {
                true -> {
                    binding.clNologin.visibility = View.INVISIBLE
                    isLogin = it
                }

                false -> {
                    binding.clNologin.visibility = View.VISIBLE
                    binding.tvLoginTag.paintFlags = Paint.UNDERLINE_TEXT_FLAG
                    isLogin = it
                }
            }
        }
    }

    private fun setupSelectedTabItem(category: String) {

        when (category) {
            "finance" -> {
                selectTabItem(category, FINANCE_TABITEM_POSION)
            }

            "communication" -> {
                selectTabItem(category, COMMUNICATION_TABITEM_POSION)
            }

            "values" -> {
                selectTabItem(category, VALUES_TABITEM_POSION)
            }

            "lifestyle" -> {
                selectTabItem(category, LIFESTYLE_TABITEM_POSION)
            }

            "child" -> {
                selectTabItem(category, CHILD_TABITEM_POSION)
            }

            "family" -> {
                selectTabItem(category, FAMILY_TABITEM_POSION)
            }

            "sex" -> {
                selectTabItem(category, SEX_TABITEM_POSION)
            }

            "health" -> {
                selectTabItem(category, HEALTH_TABITEM_POSION)
            }

            "wedding" -> {
                selectTabItem(category, WEDDING_TABITEM_POSION)
            }

            "future" -> {
                selectTabItem(category, FUTURE_TABITEM_POSION)
            }

            "present" -> {
                selectTabItem(category, PRESENT_TABITEM_POSION)
            }

            "past" -> {
                selectTabItem(category, PAST_TABITEM_POSION)
            }
        }
    }

    private fun initListener() {

        binding.tvLoginTag.setOnClickListener {
            //TODO("Home화면으로 이동 로직 구현")
        }

        binding.tb.onTabSelected {
            when (it.position) {
                FINANCE_TABITEM_POSION -> {
                    viewModel.requestQuestion("finance")
                }

                COMMUNICATION_TABITEM_POSION -> {
                    viewModel.requestQuestion("communication")
                }

                VALUES_TABITEM_POSION -> {
                    viewModel.requestQuestion("values")
                }

                LIFESTYLE_TABITEM_POSION -> {
                    viewModel.requestQuestion("lifestyle")
                }

                CHILD_TABITEM_POSION -> {
                    viewModel.requestQuestion("child")
                }

                FAMILY_TABITEM_POSION -> {
                    viewModel.requestQuestion("family")
                }

                SEX_TABITEM_POSION -> {
                    viewModel.requestQuestion("sex")
                }

                HEALTH_TABITEM_POSION -> {
                    viewModel.requestQuestion("health")
                }

                WEDDING_TABITEM_POSION -> {
                    viewModel.requestQuestion("wedding")
                }

                FUTURE_TABITEM_POSION -> {
                    viewModel.requestQuestion("future")
                }

                PRESENT_TABITEM_POSION -> {
                    viewModel.requestQuestion("present")
                }

                PAST_TABITEM_POSION -> {
                    viewModel.requestQuestion("past")
                }
            }
        }
    }

    private fun observeCategory() {
        viewModel.questions.observe(viewLifecycleOwner) {
            categoryAdapter.setData(it)
        }
    }

    private fun setCategoryAdapter(isLogin: Boolean) {
        categoryAdapter = CategoryAdapter(this)
        val categoryManager = object : LinearLayoutManager(requireActivity(), VERTICAL, false) {
            override fun canScrollVertically(): Boolean {
                return isLogin
            }
        }
        binding.rv.apply {
            layoutManager = categoryManager
            adapter = categoryAdapter
            scrollToPosition(0)
        }
    }

    private fun selectTabItem(category: String, index: Int) {
        lifecycleScope.launch {
            binding.tb.selectTab(binding.tb.getTabAt(index))
            delay(100)
            binding.tb.setScrollPosition(index, 0f, false)
        }
        viewModel.requestQuestion(category)
    }

    override fun onCategoryItemClick(question: Question) {
        if (isLogin) {
            val action =
                CategoryFragmentDirections.actionGlobalWriteAnswerFragment(question.questionId)
            findNavController().navigate(action)
        }
    }

    companion object {

        private const val FINANCE_TABITEM_POSION = 0
        private const val COMMUNICATION_TABITEM_POSION = 1
        private const val VALUES_TABITEM_POSION = 2
        private const val LIFESTYLE_TABITEM_POSION = 3
        private const val CHILD_TABITEM_POSION = 4
        private const val FAMILY_TABITEM_POSION = 5
        private const val SEX_TABITEM_POSION = 6
        private const val HEALTH_TABITEM_POSION = 7
        private const val WEDDING_TABITEM_POSION = 8
        private const val FUTURE_TABITEM_POSION = 9
        private const val PRESENT_TABITEM_POSION = 10
        private const val PAST_TABITEM_POSION = 11
    }
}
